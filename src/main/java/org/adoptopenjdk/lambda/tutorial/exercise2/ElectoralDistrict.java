package org.adoptopenjdk.lambda.tutorial.exercise2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DelegatingStream;
import java.util.stream.Stream;

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

    public static Set<Ballot> unspoiledBallots(Set<Ballot> votes) {
        // [your code here]

        return Collections.emptySet();
    }
}

