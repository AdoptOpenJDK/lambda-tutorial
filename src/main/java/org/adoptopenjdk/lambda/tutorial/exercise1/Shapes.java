package org.adoptopenjdk.lambda.tutorial.exercise1;

import java.util.List;

/**
 * A collection of functions which should be filled out to make tests pass.
 *
 * Each method should use a single call to shapes.forEach.
 *
 * Lambda Tutorial -- Adopt Open JDK
 * @author Graham Allan grundlefleck at gmail dot com
 */
public class Shapes {

    public static void colorAll(List<Shape> shapes, Color newColor) {
        for (Shape s: shapes) {
            s.setColor(newColor);
        }
    }

    public static void makeStringOfAllColors(List<Shape> shapes, StringBuilder stringBuilder) {
        for (Shape s: shapes) {
            stringBuilder.append(s);
        }
    }

    public static void changeColorAndMakeStringOfOldColors(List<Shape> shapes, Color newColor, StringBuilder stringBuilder) {
        for (Shape s: shapes) {
            stringBuilder.append("[" + s.getColor() + "]");
            s.setColor(newColor);
        }
    }
}
