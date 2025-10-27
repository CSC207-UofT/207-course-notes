package design_patterns.creational.factory.with_abstract_factory.shape_factories;

import design_patterns.creational.factory.shapes.Circle;
import design_patterns.creational.factory.shapes.Shape;

public class CircleFactory extends ShapeFactory {
    public Shape getShape() {
        return new Circle();
    }
}
