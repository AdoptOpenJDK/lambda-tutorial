package org.adoptopenjdk.lambda.tutorial.util;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import java.util.function.Function;

public final class FeatureMatchers {

    private FeatureMatchers() {}

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
