package org.adoptopenjdk.lambda.tutorial;

import org.adoptopenjdk.lambda.tutorial.exercise1.Color;
import org.adoptopenjdk.lambda.tutorial.exercise1.Shape;
import org.adoptopenjdk.lambda.tutorial.exercise2.Person;
import org.adoptopenjdk.lambda.tutorial.exercise2.VotingRules;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
     * Use List.filter() to produce a list containing only those Persons eligible to vote.
     *
     *
     */
    @Test
    public void getAllPersonsEligibleToVote() {
        Person tom = new Person("Tom", 24);
        Person dick = new Person("Dick", 75);
        Person harry = new Person("Harry", 17);

        List<Person> potentialVoters = Arrays.asList(tom, dick, harry);

        int legalAgeOfVoting = 18;
        List<Person> eligibleVoters = VotingRules.eligibleVoters(potentialVoters, legalAgeOfVoting);

        assertThat(eligibleVoters, hasSize(2));
        assertThat(eligibleVoters, containsInAnyOrder(aPersonNamed("Tom"), aPersonNamed("Dick")));

        // ensure we haven't modified the original collection
        assertThat(potentialVoters, hasSize(3));
        assertThat(potentialVoters, containsInAnyOrder(aPersonNamed("Tom"), aPersonNamed("Dick"), aPersonNamed("Harry")));
    }

    private static Matcher<Person> aPersonNamed(String name) {
        return new FeatureMatcher<Person, String>(Matchers.is(name), "is a person", "name") {
            @Override protected String featureValueOf(Person person) {
                return person.name;
            }
        };
    }
}
