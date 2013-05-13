package org.adoptopenjdk.lambda.tutorial.util;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import java.util.function.Function;

/**
 * Helper utility for matching on features
 */
public final class FeatureMatchers {

    private FeatureMatchers() {}

    /**
     * TODO Explain this method
     * 
     * @param <FROM>
     * @param <FEATURE>
     * @param featureMatcher
     * @param description
     * @param name
     * @param extractor
     * @return A Matcher
     */
    public static <FROM, FEATURE> Matcher<FROM> from(Matcher<FEATURE> featureMatcher,
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
