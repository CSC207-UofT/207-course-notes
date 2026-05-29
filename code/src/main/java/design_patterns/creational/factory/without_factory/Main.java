package design_patterns.creational.factory.without_factory;

import design_patterns.creational.factory.shapes.Circle;
import design_patterns.creational.factory.shapes.Rectangle;
import design_patterns.creational.factory.shapes.Shape;
import design_patterns.creational.factory.shapes.Square;

public class Main {

    // this is essentially the simple factory, but we would need to rewrite this same conditional
    // everywhere in our code where we need to check what kind of shape is needed.
    public static void main(String[] args) {
        String type = "Circle"; // could come from user input

        Shape shape;

        switch (type) {
            case "Circle":
                shape = new Circle();
                break;
            case "Square":
                shape = new Square();
                break;
            case "Rectangle":
                shape = new Rectangle();
                break;
            default:
                throw new IllegalArgumentException("Unknown shape type: " + type);
        }

        shape.draw();
    }
}
