package designpatterns.creational.factory.withabstractfactory.shapefactories;

import designpatterns.creational.factory.shapes.Rectangle;
import designpatterns.creational.factory.shapes.Shape;

/** A factory that creates rectangle shapes. */
public class RectangleFactory extends ShapeFactory {
  public Shape getShape() {
    return new Rectangle();
  }
}
