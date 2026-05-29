package design_patterns.creational.factory.with_abstract_factory.shape_factories;

import design_patterns.creational.factory.shapes.Shape;
import design_patterns.creational.factory.shapes.Rectangle;

public class RectangleFactory extends ShapeFactory {
    public Shape getShape() {
        return new Rectangle();
    }
}
