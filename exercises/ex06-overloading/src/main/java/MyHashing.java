/**
 * Exercise (Chapter 2: Classes) — overloading, constructors, and static methods.
 *
 * "Overloading" means having several methods (or constructors) with the same
 * name that differ in their parameters. Complete the bodies below so the three
 * `hash` methods and the two constructors behave as documented, then run
 * MyHashingTest. Edit only this file.
 *
 * Relevant reading: 2.4. Constructors, 2.5. Overloading methods, 2.7. Static
 * methods.
 */
public class MyHashing {

  /** The seed carried by this object; updated by the instance hash methods. */
  private int seed;

  /** A shared constant used by the char-based hash. */
  public static final int MODULO = 42;

  /** Creates a MyHashing whose seed starts at 0. */
  public MyHashing() {
    // TODO: this constructor takes no arguments; leave the seed at its default.
  }

  /**
   * Creates a MyHashing with the given starting seed.
   *
   * @param seed the initial seed value
   */
  public MyHashing(int seed) {
    // TODO: store the parameter in this object's seed field.
  }

  /**
   * Stores {@code value} as the new seed and returns the <em>previous</em> seed.
   *
   * @param value the new seed
   * @return the seed value from before this call
   */
  public int hash(int value) {
    // TODO
    return 0;
  }

  /**
   * Stores {@code value} as the new seed and returns the sum of the previous
   * seed and {@code value}, taken modulo {@link #MODULO}. (A char used in
   * arithmetic is automatically treated as its numeric code, e.g. 'A' is 65.)
   *
   * @param value the new seed, as a character
   * @return (previous seed + value) % MODULO
   */
  public int hash(char value) {
    // TODO
    return 0;
  }

  /**
   * Returns the sum of the numeric codes of the characters in {@code value}.
   * This is a static (class) method: it belongs to the class, not to any one
   * object, so it has no seed to read or change.
   *
   * @param value the string to hash
   * @return the sum of the characters' numeric codes
   */
  public static int hash(String value) {
    // TODO: String.toCharArray() may help.
    return 0;
  }
}
