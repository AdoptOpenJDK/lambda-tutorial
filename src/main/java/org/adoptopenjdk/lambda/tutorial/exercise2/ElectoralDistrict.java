package org.adoptopenjdk.lambda.tutorial.exercise2;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Lambda Tutorial -- Adopt Open JDK
 *
 * @author Graham Allan grundlefleck at gmail dot com
 */
public enum ElectoralDistrict {

    // Some (inaccurate) London electrical districts
    CROYDON("CR"),
    BARKING("BA"),
    HACKNEY("HA"),
    EDMONTON("ED");

    // ... ~ 650 more for the UK

    public final String prefix;

    ElectoralDistrict(String prefix) {
        this.prefix = prefix;
    }

    public static Set<RegisteredVoter> votersIn(ElectoralDistrict district, Collection<RegisteredVoter> voters) {
        Set<RegisteredVoter> fromDistrict = voters.stream()
                .filter(v -> v.getElectorId().startsWith(district.prefix))
                .collect(Collectors.toSet());

        return Collections.unmodifiableSet(fromDistrict);
    }

    public static Set<Ballot> unspoiledBallots(Set<Ballot> votes) {
        return votes.stream()
                .filter(v -> !v.isSpoiled())
                .collect(Collectors.toSet());
    }
}

