package designpatterns.creational.factory.shapes;

/** A circle shape. */
public class Circle implements Shape {

  @Override
  public void draw() {
    System.out.println("A circle is drawn.");
  }
}
