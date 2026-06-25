import java.util.Objects;

/**
 * Exercise (Chapter 2: Classes) — overriding {@code toString}, {@code equals},
 * and {@code hashCode}.
 *
 * Every class inherits these methods from {@code Object}, but the defaults
 * compare by identity (are these the *same* object?) rather than by value.
 * Override the three methods below so two points with the same coordinates are
 * treated as equal. Edit only this file.
 *
 * Remember the contract: if {@code a.equals(b)} is true, then
 * {@code a.hashCode() == b.hashCode()} must also be true.
 *
 * Relevant reading: 2.6.1. toString, 2.6.2. equals, 2.6.3. hashCode.
 */
public class Point {

  private final int x;
  private final int y;

  /**
   * Creates a point.
   *
   * @param x the x-coordinate
   * @param y the y-coordinate
   */
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  /**
   * Returns this point formatted as {@code "(x, y)"} — for example
   * {@code "(3, 4)"}.
   *
   * @return the string form of this point
   */
  @Override
  public String toString() {
    // TODO
    return "";
  }

  /**
   * Returns whether {@code o} is a Point with the same x and y as this one.
   *
   * @param o the object to compare with
   * @return true iff o is a Point with equal coordinates
   */
  @Override
  public boolean equals(Object o) {
    // TODO: check that o is a Point (use `instanceof`), cast it, and compare
    //       the x and y fields.
    return false;
  }

  /**
   * Returns a hash code consistent with {@link #equals(Object)} — equal points
   * must return the same value.
   *
   * @return a hash code derived from x and y
   */
  @Override
  public int hashCode() {
    // TODO: Objects.hash(x, y) is an easy way to combine the fields.
    return 0;
  }
}
