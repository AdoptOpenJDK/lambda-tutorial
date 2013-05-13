package org.adoptopenjdk.lambda.tutorial;

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

import org.adoptopenjdk.lambda.tutorial.exercise1.Color;
import org.adoptopenjdk.lambda.tutorial.exercise1.Shape;
import org.adoptopenjdk.lambda.tutorial.exercise1.Shapes;
import org.adoptopenjdk.lambda.tutorial.util.FeatureMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.adoptopenjdk.lambda.tutorial.exercise1.Color.BLACK;
import static org.adoptopenjdk.lambda.tutorial.exercise1.Color.BLUE;
import static org.adoptopenjdk.lambda.tutorial.exercise1.Color.GREEN;
import static org.adoptopenjdk.lambda.tutorial.exercise1.Color.RED;
import static org.adoptopenjdk.lambda.tutorial.exercise1.Color.YELLOW;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasSize;

/**
 * Exercise 1 - Internal vs External iteration.
 * <p>
 * As described in Brian Goetz's State of the Lambda - Libraries Edition[0], Java's collection classes provide a way for
 * clients to enumerate the members of a collection. Currently, this is iteration is "External" - that is, the
 * collection can be iterated in sequence, by the client code.
 * </p>
 * <p>
 * This refers to the trusty "for loop":
 * TODO Bug in the Javdoc processor? It claims the code annotation construct is invalid
 * <pre>
 * {@code
 *    for (Shape s: shapes) {
 *       s.setColor(RED)
 *    }
 * }
 * </pre>
 * </p>
 * <p>
 * JDK 8, with lambdas and an updated Collections library, will allow "Internal" iteration. In this case, the collection
 * receives some code, and decides how to apply that to its elements. This has several benefits, including:
 *  - allowing the collection to decide how to handle executing given code, including opening the door to parallelism and laziness
 *  - leads to a style where operations can be pipelined, into a more fluent, readable style.
 * </p>
 * <p>
 * Internal iteration, using lambda expression syntax, turns the above for loop into:
 * <pre>
 * {@code 
 * shapes.forEach(s -> s.setColor(RED))
 * }
 * </pre>
 * Where <code>s -> s.setColor(RED)</code> is a lambda expression, which is passed into forEach, and invoked inside forEach. Lambda
 * expressions have a type, called a "Functional Interface". In this case the lambda is of type Consumer. Consumer declares
 * a single method, <code>void accept(T t)</code>. This is all hidden by the Java compiler, and unless you extract the lambda 
 * to a variable, or write a method which accepts a lambda, you don't really need to know about it.
 * </p>
 * <p>
 * The tests below can be made to pass using for loops. Try to make them pass without using a for loop.
 * </p>
 * [0] http://cr.openjdk.java.net/~briangoetz/lambda/sotc3.html
 * 
 * @see java.lang.Iterable#forEach
 * @see Shape
 * @see Shapes
 * @see Color
 *
 * @author Graham Allan grundlefleck at gmail dot com
 */
@SuppressWarnings("unchecked")
public class Exercise_1_Test {

    /**
     * Use forEach to change the color of every shape to RED.
     *
     * Change the 'method under test' to make the test pass.
     *
     * @see Shapes#colorAll(java.util.List, Color)
     *
     * @see Iterable#forEach
     * @see Shape#setColor(Color)
     */
    @Test
    public void changeColorOfAllShapes() {
        List<Shape> myShapes = Arrays.asList(new Shape(BLUE), new Shape(BLACK), new Shape(YELLOW));

        // method under test
        Shapes.colorAll(myShapes, RED);

        assertThat(myShapes, hasSize(3));
        assertThat(myShapes, everyItem(hasColor(RED)));
    }

    /**
     * Use forEach, with the given StringBuilder, to assemble a String which represents every Shape in the given list.
     *
     * Change the 'method under test' to make the test pass.
     *
     * The String should be a concatenation of each Shape's toString.
     *
     * @see Shapes#makeStringOfAllColors(java.util.List, StringBuilder)
     *
     * @see Shape#toString()
     * @see StringBuilder#append(String)
     * @see Iterable#forEach
     */
    @Test
    public void buildStringRepresentingAllShapes() {
        List<Shape> allMyShapes = Arrays.asList(new Shape(RED), new Shape(BLACK), new Shape(YELLOW));
        StringBuilder builder = new StringBuilder();

        // method under test
        Shapes.makeStringOfAllColors(allMyShapes, builder);

        assertThat(builder.toString(), equalTo("[a RED shape][a BLACK shape][a YELLOW shape]"));
    }

    /**
     * Use forEach to change the color, and build a string showing the old colors of each shape.
     *
     * Try to perform both actions using a single call to forEach.
     *
     * @see Shapes#changeColorAndMakeStringOfOldColors
     *
     * @see Shape#getColor()
     * @see Color#name()
     * @see StringBuilder#append(String)
     */
    @Test
    public void changeColorOfAllShapes_AND_buildStringShowingAllTheOldColors() {
        List<Shape> myShapes = Arrays.asList(new Shape(BLUE), new Shape(BLACK), new Shape(YELLOW));
        StringBuilder builder = new StringBuilder();

        Shapes.changeColorAndMakeStringOfOldColors(myShapes, GREEN, builder);

        assertThat(myShapes, hasSize(3));
        assertThat(myShapes, everyItem(hasColor(GREEN)));
        assertThat(builder.toString(), equalTo("[a BLUE shape][a BLACK shape][a YELLOW shape]"));
    }

    // ----- Test helpers -----

    private static Matcher<Shape> hasColor(Color color) {
        return FeatureMatchers.from(Matchers.is(color), "has color", "color", Shape::getColor);
    }
}
