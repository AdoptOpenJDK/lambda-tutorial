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

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.ComparisonFailure;

public class StringWithComparisonMatcher extends TypeSafeDiagnosingMatcher<String> {
    private final String expected;

    private StringWithComparisonMatcher(String expected) {
        this.expected = expected;
    }

    public static StringWithComparisonMatcher isString(String expected) {
        return new StringWithComparisonMatcher(expected);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(expected);
    }

    @Override
    protected boolean matchesSafely(String actual, Description mismatchDescription) {
        if (!expected.equals(actual)) {
            String compactedMismatch = new ComparisonFailure("did not match:", expected, actual).getMessage();
            mismatchDescription.appendText(compactedMismatch);
            return false;
        }
        return true;
    }

}