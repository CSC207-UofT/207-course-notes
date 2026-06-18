package designpatterns.creational.factory.withabstractfactory.shapefactories;

import designpatterns.creational.factory.shapes.Shape;

/** Abstract factory that creates shapes. */
public abstract class ShapeFactory {
  /**
   * Creates a shape.
   *
   * @return a new shape
   */
  public abstract Shape getShape();
}
