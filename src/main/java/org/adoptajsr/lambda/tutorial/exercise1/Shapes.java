package org.adoptajsr.lambda.tutorial.exercise1;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import static java.awt.Color.BLUE;

/**
 *
 *
 * Lambda Tutorial -- Adopt Open JDK
 * @author Graham Allan grundlefleck at gmail dot com
 */
public class Shapes {

    private final List<Shape> shapes;

    public Shapes(Shape... shapes) {
        this.shapes = Arrays.asList(shapes);
    }

    public List<Shape> underlyingList() {
        return shapes;
    }

    public void colorAll(Color newColor) {
        // [your code here]
    }

    public void colorAllBlueShapes(Color newColor) {
        // [your code here]
    }


}
