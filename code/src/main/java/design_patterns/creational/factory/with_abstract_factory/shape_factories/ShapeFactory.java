package design_patterns.creational.factory.with_abstract_factory.shape_factories;

import design_patterns.creational.factory.shapes.Shape;

public abstract class ShapeFactory {
    public abstract Shape getShape();
}
