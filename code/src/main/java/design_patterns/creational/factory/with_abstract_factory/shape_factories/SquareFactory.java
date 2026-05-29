package design_patterns.creational.factory.with_abstract_factory.shape_factories;

import design_patterns.creational.factory.shapes.Square;
import design_patterns.creational.factory.shapes.Shape;

public class SquareFactory extends ShapeFactory {
    public Shape getShape() {
        return new Square();
    }
}
