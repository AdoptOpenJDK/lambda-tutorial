package org.adoptopenjdk.lambda.tutorial.exercise2;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * Some (inaccurate) London electrical districts
 * 
 * Lambda Tutorial -- Adopt Open JDK
 *
 * @author Graham Allan grundlefleck at gmail dot com
 */
public enum ElectoralDistrict {

    /** Kate Moss's home district */
    CROYDON("CR"),
    /** District line end point */
    BARKING("BA"),
    /** Hipsterville */
    HACKNEY("HA"),
    /** Edmonton */
    EDMONTON("ED");

    private final String prefix;

    ElectoralDistrict(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Complete this method to pass Exercise_2_Test#setOfVotersInDistrict()
     * 
     * @param district - District to vote in
     * @param voters - voters to filter
     * @return filtered set of registered voters in a district
     */
    public static Set<RegisteredVoter> votersIn(ElectoralDistrict district, Collection<RegisteredVoter> voters) {
        // [your code here]

        return Collections.emptySet();
    }

    /**
     * Complete this method to pass Exercise_2_Test#removeAllSpoiledBallots()
     * 
     * @param votes - votes to filter
     * @return filtered set of unspoiled ballots
     */
    public static Set<Ballot> unspoiledBallots(Set<Ballot> votes) {
        // [your code here]

        return Collections.emptySet();
    }

    /**
     * Get the district's prefix
     * @return prefix
     */
    public String getPrefix() {
        return prefix;
    }
}

