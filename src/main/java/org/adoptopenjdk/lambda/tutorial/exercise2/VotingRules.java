package org.adoptopenjdk.lambda.tutorial.exercise2;

import java.util.List;

public class VotingRules {
    public static List<Person> eligibleVoters(List<Person> potentialVoters, int legalAgeOfVoting) {
        return potentialVoters.stream()
            .filter(p -> p.getAge() >= legalAgeOfVoting)
            .collect(Collectors.toList());
    }
}
