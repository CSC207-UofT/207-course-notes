package designpatterns.creational.factory.withsimplefactory;

import designpatterns.creational.factory.shapes.Circle;
import designpatterns.creational.factory.shapes.Rectangle;
import designpatterns.creational.factory.shapes.Shape;
import designpatterns.creational.factory.shapes.Square;

// Simple Factory
/** A simple factory that creates shapes based on a type string. */
public class SimpleShapeFactory {
  /**
   * Creates a shape for the given type.
   *
   * @param type the shape type, such as "Circle", "Square", or "Rectangle"
   * @return a new shape of the requested type
   */
  public Shape getShape(String type) {
    switch (type) {
      case "Circle":
        return new Circle();
      case "Square":
        return new Square();
      case "Rectangle":
        return new Rectangle();
      default:
        throw new IllegalArgumentException("Unknown shape type: " + type);
    }
  }

  // Client code
  /**
   * Creates a shape via the simple factory and draws it.
   *
   * @param args command-line arguments (unused)
   */
  public static void main(String[] args) {
    Shape shape = new SimpleShapeFactory().getShape("Circle");
    shape.draw();
  }
}
