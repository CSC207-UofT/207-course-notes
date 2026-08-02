/**
 * Exercise (Chapter 6: Generics) — a custom generic class and a bounded method.
 *
 * {@code Box<T>} is a generic class: {@code T} is a type parameter that is
 * filled in when you create a box, e.g. {@code new Box<String>()}. Complete the
 * instance methods so a box can store and return a value of its type, and
 * complete the bounded generic method {@link #max}. Edit only this file.
 *
 * Relevant reading: 6.1 Custom Generic Classes, 6.2 bounded type parameters.
 */
public class Box<T> {

  private T item;

  /**
   * Stores {@code item} in this box.
   *
   * @param item the value to store
   */
  public void set(T item) {
    // TODO: store item in this box's field (mind the shadowing — use `this`).
  }

  /**
   * Returns the value currently stored in this box (or null if none).
   *
   * @return the stored value
   */
  public T get() {
    // TODO
    return null;
  }

  /**
   * Returns whether this box is empty (holds no item).
   *
   * @return true iff no item has been stored
   */
  public boolean isEmpty() {
    // TODO
    return false;
  }

  /**
   * Returns the larger of {@code a} and {@code b}. The bound
   * {@code <T extends Comparable<T>>} guarantees the values can be compared with
   * {@code compareTo}.
   *
   * @param a the first value
   * @param b the second value
   * @param <T> a type that is comparable with itself
   * @return whichever of a and b is greater (a if they are equal)
   */
  public static <T extends Comparable<T>> T max(T a, T b) {
    // TODO: use a.compareTo(b) to decide which to return.
    return a;
  }
}
