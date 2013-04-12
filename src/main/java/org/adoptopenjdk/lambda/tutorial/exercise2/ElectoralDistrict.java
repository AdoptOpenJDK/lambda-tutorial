package org.adoptopenjdk.lambda.tutorial.exercise2;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
        Set<RegisteredVoter> votersInDistrict = new HashSet<>();
        for (RegisteredVoter v: voters) {
            if (v.electorId.startsWith(district.prefix)) {
                votersInDistrict.add(v);
            }
        }

        return votersInDistrict;
    }
}
