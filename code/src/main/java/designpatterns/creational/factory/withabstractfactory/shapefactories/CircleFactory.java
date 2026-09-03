package designpatterns.creational.factory.withabstractfactory.shapefactories;

import designpatterns.creational.factory.shapes.Circle;
import designpatterns.creational.factory.shapes.Shape;

/** A factory that creates circle shapes. */
public class CircleFactory extends ShapeFactory {
  public Shape getShape() {
    return new Circle();
  }
}
