package design_patterns.creational.factory.with_simple_factory;

import design_patterns.creational.factory.shapes.Circle;
import design_patterns.creational.factory.shapes.Rectangle;
import design_patterns.creational.factory.shapes.Shape;
import design_patterns.creational.factory.shapes.Square;

// Simple Factory
public class SimpleShapeFactory {
    public Shape getShape(String type) {
        switch (type) {
            case "Circle": return new Circle();
            case "Square": return new Square();
            case "Rectangle": return new Rectangle();
            default: throw new IllegalArgumentException("Unknown shape type: " + type);
        }
    }

    // Client code
    public static void main(String[] args) {
            Shape shape = new SimpleShapeFactory().getShape("Circle");
            shape.draw();
    }
}
