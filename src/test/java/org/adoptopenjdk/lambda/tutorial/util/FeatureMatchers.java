package org.adoptopenjdk.lambda.tutorial.util;

/*
 * #%L
 * lambda-tutorial
 * %%
 * Copyright (C) 2013 Adopt OpenJDK
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

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
     * @param featureMatcher - Matcher that contains a feature
     * @param description - the description of the feature
     * @param name - the name of the feature
     * @param extractor - the Function used to extract the feature
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
