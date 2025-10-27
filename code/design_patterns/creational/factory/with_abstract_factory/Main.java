package design_patterns.creational.factory.with_abstract_factory;


import design_patterns.creational.factory.shapes.Shape;
import design_patterns.creational.factory.with_abstract_factory.shape_factories.CircleFactory;
import design_patterns.creational.factory.with_abstract_factory.shape_factories.ShapeFactory;

// Client code
public class Main {
    public static void main(String[] args) {
        ShapeFactory factory = new CircleFactory(); // could be chosen dynamically
        Shape shape = factory.getShape();
        shape.draw();
    }
}
