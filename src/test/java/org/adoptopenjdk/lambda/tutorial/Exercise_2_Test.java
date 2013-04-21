package org.adoptopenjdk.lambda.tutorial;

import org.adoptopenjdk.lambda.tutorial.exercise2.Ballot;
import org.adoptopenjdk.lambda.tutorial.exercise2.ElectoralDistrict;
import org.adoptopenjdk.lambda.tutorial.exercise2.Party;
import org.adoptopenjdk.lambda.tutorial.exercise2.Person;
import org.adoptopenjdk.lambda.tutorial.exercise2.RegisteredVoter;
import org.adoptopenjdk.lambda.tutorial.exercise2.VotingRules;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Arrays.binarySearch;
import static org.adoptopenjdk.lambda.tutorial.exercise2.ElectoralDistrict.HACKNEY;
import static org.adoptopenjdk.lambda.tutorial.exercise2.ElectoralDistrict.votersIn;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Exercise 2 - Filtering and Collecting
 *
 * Filtering is one of the most popular operations performed on collections. When coupled with mapping/transforming,
 * which we'll learn about shortly, it provides a lot of power.
 *
 * Consider how often you have written code like this:
 *
 *     List<String> things = new ArrayList<>();
 *     for (String s: otherThings) {
 *         if (satisfiesSomeCondition(s)) {
 *             things.add(s);
 *         }
 *     }
 *     return things;
 *
 * This is a common pattern, and it's one that is made more concise with lambdas. The common parts of the pattern are:
 *  - creating an empty, destination collection
 *  - iterating over the entire source collection
 *  - deciding whether to include each item, according to some test which evaluates to a boolean
 *      - items which pass the test are copied to the destination collection
 *      - items which fail are ignored
 *
 * These steps will rarely change, but think of how we represent that in pre JDK 8 code. We use a for (or while) loop,
 * and an if statement. Those parts are syntax that until now we couldn't escape, and they're almost always identical.
 *
 * The remaining steps (creating a collection, adding them) do have some variation: which destination collection to
 * create? how should an instance be constructed? which method is used to transfer elements to the new list? These
 * decisions generally lead to code which differs slightly between examples.
 *
 * In post-JDK 8 code, one potential solution which matches the above problem is:
 *
 *     return otherThings.stream()
 *         .filter(s -> satisfiesSomeCondition(s))
 *         .collect(Collectors.toList());
 *
 * In this case, unlike the previous example where we used forEach directly from List, in this case we have to "open up"
 * the Streams API by calling the stream() method defined on Collection. This provides many operations which take
 * advantage of the lambda syntax.
 *
 * One such method available on Stream is filter(). In the first code snippet, we perform our filtering using
 * if (satisfiesSomeCondition(s)). The code inside the if condition can be referred to as a "predicate". That is, a
 * function which takes some input, and returns a boolean. This is how the filter() method is defined: it takes a
 * lambda, which, when given an element from the Stream, returns a boolean. Internally, the filter method has the
 * equivalent semantics of the for loop, and the if statement. It will return a new Stream, containing
 * all the elements which passed the test (or "satisfied the predicate") from the source Stream.
 *
 * The last piece in this snippet of code (".collect(Collectors.toList())") is what takes the place of constructing
 * the initial empty list, and invoking the add() method. The code used in these examples depends on what type of
 * list is best suited for the resultant list, and also, how best to transfer elements from the result Stream into
 * the new list. The very common case, of creating a new ArrayList, and simply calling add() for each element, is
 * fulfilled by Collectors.toList.
 *
 * If you want your result to be a different type of collection, or potentially, something completely outwith the
 * collections library, and you wanted to control over how those elements were transferred, you would write your own
 * collector. In most cases, and in these examples, we'll use only simple, pre-existing collectors, available in the
 * JDK.
 *
 * The below tests can be made to pass using Stream's filter and collect methods. Try to make them pass without using
 * a loop, or an if statement.
 *
 *
 * @see Collection#stream()
 * @see Stream#filter(Predicate)
 * @see Predicate
 * @see Collector
 * @see Collectors
 * @see Collectors#toList()
 *
 * Lambda Tutorial -- Adopt Open JDK
 * @author Graham Allan grundlefleck at gmail dot com
 */
@SuppressWarnings("unchecked")
public class Exercise_2_Test {

    /**
     * Use Stream.filter() to produce a list containing only those Persons eligible to vote.
     *
     * @see Person#age
     */
    @Test
    public void getAllPersonsEligibleToVote() {
        List<Person> potentialVoters =
                new ArrayList<>(asList(new Person("Tom", 24), new Person("Dick", 75), new Person("Harry", 17)));

        int legalAgeOfVoting = 18;
        List<Person> eligibleVoters = VotingRules.eligibleVoters(potentialVoters, legalAgeOfVoting);

        assertThat(eligibleVoters, hasSize(2));
        assertThat(eligibleVoters, containsInAnyOrder(aPersonNamed("Tom"), aPersonNamed("Dick")));

        // ensure we haven't modified the original collection
        assertThat(potentialVoters, hasSize(3));
        assertThat(potentialVoters, containsInAnyOrder(aPersonNamed("Tom"), aPersonNamed("Dick"), aPersonNamed("Harry")));
    }

    /**
     * Uses Stream.filter() to find all the voters residing in a given district.
     *
     * The resulting collection is to be used for quick lookups to find if a given
     * voter resides in a district. Performance measurements indicate we should
     * prefer the result to be a java.util.Set. Use Stream.collect() to produce a
     * Set containing the result, rather than a List.
     *
     * HINT: sometimes type inference is "not there yet", help out the compiler
     * with explicit generic arguments if you have to.
     *
     * @see Stream#collect(java.util.stream.Collector)
     * @see java.util.stream.Collectors#toSet()
     *
     * @see ElectoralDistrict#prefix
     * @see RegisteredVoter#electorId
     */
    @Test public void setOfVotersInDistrict() {
        List<RegisteredVoter> allVoters = new ArrayList<>(asList(
            new RegisteredVoter("CR2345"),
            new RegisteredVoter("HA7654"),
            new RegisteredVoter("HA2213"),
            new RegisteredVoter("BA9987"),
            new RegisteredVoter("CR6203"),
            new RegisteredVoter("ED9876")
            // ... and many more
        ));

        Set<RegisteredVoter> votersInHackney = ElectoralDistrict.votersIn(HACKNEY, allVoters);

        assertThat(votersInHackney, hasSize(2));
        assertThat(votersInHackney, containsInAnyOrder(aVoterWithId("HA7654"), aVoterWithId("HA2213")));
    }

    @Test public void removeAllSpoiledBallots() {
        Set<Ballot> votes = new HashSet<>(asList(
                Ballot.voteFor(Party.LABOUR),
                Ballot.voteFor(Party.CONSERVATIVE),
                Ballot.spoiled(),
                Ballot.voteFor(Party.MONSTER_RAVING_LOONY_PARTY),
                Ballot.voteFor(Party.LIBERAL_DEMOCRATS),
                Ballot.spoiled(),
                Ballot.voteFor(Party.GREEN_PARTY),
                Ballot.voteFor(Party.GREEN_PARTY)
                // ... and many more
        ));

        Set<Ballot> unspoiledBallots = ElectoralDistrict.unspoiledBallots(votes);

        assertThat(unspoiledBallots, hasSize(6));
        assertThat(unspoiledBallots, everyItem(is(not(spoiled()))));
    }

    /**
     * Ensure that the Set returned cannot be modified by callers by wrapping the result
     * in Collections.unmodifiableSet().
     *
     * The Streams API does not provide a way to wrap the final result, as one of its operations. So just wrap the
     * result in an unmodifiableSet yourself. Sometimes it's just as important to know what an API _doesn't_ do.
     *
     * @see Stream#collect(java.util.stream.Collector)
     * @see java.util.stream.Collectors#toSet()
     * @see Collections#unmodifiableSet(java.util.Set)
     */
    @Test public void setOfVotersInDistrictInUnmodifiableSet() throws ClassNotFoundException {
        List<RegisteredVoter> allVoters = new ArrayList<>(asList(
            new RegisteredVoter("CR2345"),
            new RegisteredVoter("HA7654"),
            new RegisteredVoter("HA2213"),
            new RegisteredVoter("BA9987"),
            new RegisteredVoter("CR6203"),
            new RegisteredVoter("ED9876")
            // ... and many more
        ));

        Set<RegisteredVoter> votersInHackney = ElectoralDistrict.votersIn(HACKNEY, allVoters);

        assertThat(votersInHackney, instanceOf(Class.forName("java.util.Collections$UnmodifiableSet")));
    }

    // Test helpers

    private static Matcher<Person> aPersonNamed(String name) {
        return featureMatcher(is(name), "a person", "name", p -> p.name);
    }

    private static Matcher<RegisteredVoter> aVoterWithId(String name) {
        return featureMatcher(is(name), "a voter", "electorId", v -> v.electorId);
    }

    private static Matcher<Ballot> spoiled() {
        return featureMatcher(equalTo(true), "a spoiled ballot", "isSpoiled", b -> b.isSpoiled);
    }

    private static <FROM, FEATURE> Matcher<FROM> featureMatcher(Matcher<FEATURE> featureMatcher,
                                                                String description,
                                                                String name,
                                                                Function<FROM, FEATURE> extractor) {
        return new FeatureMatcher<FROM, FEATURE>(featureMatcher, description, name) {
            @Override protected FEATURE featureValueOf(FROM t) {
                return extractor.apply(t);
            }
        };
    }

}
