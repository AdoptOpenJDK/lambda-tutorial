package org.adoptopenjdk.lambda.setupcheck;

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

import java.util.List;
import static java.util.Arrays.asList;

/**
 * Utility class to warn users if they have an incorrect environment
 * 
 * @author Graham Allan grundlefleck at gmail dot com
 */
public class ConfigureYourLambdaBuildOfJdk {

    /**
     * Utility method to warn users if they have an incorrect environment
     * @param args - should be <code>null</code>
     */
    public static void main(String... args) {
        List<String> messages =  asList(
            "If this source file does not compile, you have not configured your development setup correctly.",
            "It uses both a JDK 8+ syntax (method references with '::') and a JDK 8+ library method (Iterable#forEach)",
            "You should also be able to execute this main method, and see this message printed to the console.",
            "",
            "To configure your development environment, you need:",
            "    - an install of JDK 8 or higher",
            "    - an IDE that supports JDK8+. All mainstream IDEs (Eclipse/IntelliJ IDEA/NetBeans) support JDK8+ ",
            "    - and/or Maven, to compile your code and run your tests, using 'mvn test'",
            "",
            "Until this source file compiles, you will be unable to make progress in the tutorial.");

        messages.forEach(System.out::println);
    }
}
