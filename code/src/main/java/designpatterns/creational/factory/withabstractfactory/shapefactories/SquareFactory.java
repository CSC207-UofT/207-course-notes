package designpatterns.creational.factory.withabstractfactory.shapefactories;

import designpatterns.creational.factory.shapes.Shape;
import designpatterns.creational.factory.shapes.Square;

/** A factory that creates square shapes. */
public class SquareFactory extends ShapeFactory {
  public Shape getShape() {
    return new Square();
  }
}
