package org.adoptajsr.lambda.tutorial.exercise1;


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
 * Lambda Tutorial -- Adopt Open JDK
 *
 *
 * @author Graham Allan grundlefleck at gmail dot com
 */
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



