package designpatterns.creational.factory.shapes;

/** A square shape. */
public class Square implements Shape {

  @Override
  public void draw() {
    System.out.println("A square is drawn.");
  }
}
