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
        shapes.forEach(s -> s.setColor(newColor));
    }

    public static void makeStringOfAllColors(List<Shape> shapes, StringBuilder stringBuilder) {
        shapes.forEach(s -> stringBuilder.append(s));
    }

    public static void changeColorAndMakeStringOfOldColors(List<Shape> shapes, Color newColor, StringBuilder stringBuilder) {
        shapes.forEach(s -> { stringBuilder.append("[" + s.getColor() + "]"); s.setColor(newColor); });

    }
}
