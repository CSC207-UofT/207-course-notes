package designpatterns.creational.factory.withabstractfactory;

import designpatterns.creational.factory.shapes.Shape;
import designpatterns.creational.factory.withabstractfactory.shapefactories.CircleFactory;
import designpatterns.creational.factory.withabstractfactory.shapefactories.ShapeFactory;

// Client code
/** Demonstrates creating shapes using the abstract factory pattern. */
public class Main {
  /**
   * Creates a shape via a concrete factory and draws it.
   *
   * @param args command-line arguments (unused)
   */
  public static void main(String[] args) {
    ShapeFactory factory = new CircleFactory(); // could be chosen dynamically
    Shape shape = factory.getShape();
    shape.draw();
  }
}
