package designpatterns.creational.factory.shapes;

/** A rectangle shape. */
public class Rectangle implements Shape {

  @Override
  public void draw() {
    System.out.println("A rectangle is drawn.");
  }
}
