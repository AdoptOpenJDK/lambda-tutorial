package org.adoptopenjdk.lambda.tutorial.exercise1;


import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.awt.Color;

import static java.awt.Color.BLACK;
import static java.awt.Color.BLUE;
import static java.awt.Color.RED;
import static java.awt.Color.YELLOW;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.contains;


/**
 * Exercise 1 - Internal vs External iteration.
 *
 * As described in Brian Goetz's State of the Lambda - Libraries Edition[0], Java's collection classes provide a way for
 * clients to enumerate the members of a collection. Currently, this is iteration is "External" - that is, the
 * collection can be iterated in sequence, by the client code.
 *
 * This refers to the trusty "for loop":
 *
 *   for (Shape s: shapes) {
 *       s.setColor(RED)
 *   }
 *
 * JDK 8, with lambdas and an updated Collections library, will allow "Internal" iteration. In this case, the collection
 * receives some code, and decides how to apply that to its elements. This has several benefits, including:
 *  - allowing the collection to decide how to handle executing given code, including opening the door to parallelism and laziness
 *  - leads to a style where operations can be pipelined, into a more fluent, readable style.
 *
 *  Internal iteration, using lambda expression syntax, turns the above for loop into:
 *
 *  shapes.forEach(s -> s.setColor(RED))
 *
 *
 * [0] http://cr.openjdk.java.net/~briangoetz/lambda/sotc3.html
 *
 * Lambda Tutorial -- Adopt Open JDK
 * @author Graham Allan grundlefleck at gmail dot com
 */
@SuppressWarnings("unchecked")
public class ShapesTest {

    @Test
    public void changeColorOfAllShapes() {
        Shape first = new Shape(RED);
        Shape second = new Shape(BLACK);
        Shape third = new Shape(YELLOW);

        Shapes allMyShapes = new Shapes(first, second, third);

        allMyShapes.colorAll(RED);

        assertThat(allMyShapes.underlyingList(), hasSize(3));
        assertThat(allMyShapes.underlyingList(), everyItem(hasColor(RED)));
    }

    @Test
    public void changeColorOfAllBlueShapes() {
        Shape first = new Shape(BLUE);
        Shape second = new Shape(BLACK);
        Shape third = new Shape(YELLOW);

        Shapes allMyShapes = new Shapes(first, second, third);

        allMyShapes.colorAllBlueShapes(RED);

        assertThat(allMyShapes.underlyingList(), hasSize(3));
        assertThat(allMyShapes.underlyingList(), contains(hasColor(RED), hasColor(BLACK), hasColor(YELLOW)));
    }


    private static Matcher<Shape> hasColor(Color color) {
        return new FeatureMatcher<Shape, Color>(Matchers.is(color), "has color", "color") {
            @Override protected Color featureValueOf(Shape shape) {
                return shape.getColor();
            }
        };
    }
}



