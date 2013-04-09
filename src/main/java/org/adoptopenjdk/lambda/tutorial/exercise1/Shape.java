package org.adoptopenjdk.lambda.tutorial.exercise1;

import java.awt.Color;

/**
 * A simplified, mutable version of java.awt.Shape.
 *
 *
 * Lambda Tutorial -- Adopt Open JDK
 * @author Graham Allan grundlefleck at gmail dot com
 */
public class Shape {

    private Color color;

    public Shape(Color color) {
        this.color = color;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
