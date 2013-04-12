package org.adoptopenjdk.lambda.tutorial.exercise2;

import java.util.Collection;
import java.util.Collections;
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
        // [your code here]

        return Collections.emptySet();
    }
}
