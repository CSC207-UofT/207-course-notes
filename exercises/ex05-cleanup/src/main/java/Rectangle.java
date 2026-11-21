/**
 * Created a rectangle.
 */
public class Rectangle {
  private double width;
  private double height;

  /**
   * Initiates the rectangle.
   *
   * @param w the width of the rectangle.
   * @param h the height of the rectangle.
   */
  public Rectangle(double w, double h) {
    this.width = w;
    this.height = h;
  }

  /**
   * Calculates the area of a rectangle.
   * Returns width * height.
   *
   * @return the area of the rectangle.
   */
  public double area() {
    return width * height;
  }

  /**
   * Scales the rectangle.
   *
   * @param factor to scale the rectangle.
   */
  public void scale(double factor) {
    width = width * factor;
    height = height * factor;
  }

  /**
   * Returns whether the current rectangle is larger than the other rectangle.
   *
   * @param other the other rectangle we're comparing with.
   * @return whether the original rectangle is larger
   */
  public boolean isLargerThan(Rectangle other) {
    return area() > other.area();
  }
}
