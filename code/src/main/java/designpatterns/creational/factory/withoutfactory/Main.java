package designpatterns.creational.factory.withoutfactory;

import designpatterns.creational.factory.shapes.Circle;
import designpatterns.creational.factory.shapes.Rectangle;
import designpatterns.creational.factory.shapes.Shape;
import designpatterns.creational.factory.shapes.Square;

/** Demonstrates creating shapes without using a factory. */
public class Main {

  // this is essentially the simple factory, but we would need to rewrite this same conditional
  // everywhere in our code where we need to check what kind of shape is needed.
  /**
   * Creates and draws a shape based on a hard-coded type.
   *
   * @param args command-line arguments (unused)
   */
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
