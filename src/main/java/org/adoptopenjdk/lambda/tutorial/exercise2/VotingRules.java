package org.adoptopenjdk.lambda.tutorial.exercise2;

import java.util.ArrayList;
import java.util.List;

public class VotingRules {
    public static List<Person> eligibleVoters(List<Person> potentialVoters, int legalAgeOfVoting) {
        List<Person> eligible = new ArrayList<>();
        for (Person p: potentialVoters) {
            if (p.getAge() >= legalAgeOfVoting) {
                eligible.add(p);
            }
        }
        return eligible;
    }
}
