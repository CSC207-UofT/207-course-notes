/**
 * Exercise (Chapter 2: Classes) — static vs. instance fields.
 *
 * A <em>static</em> field belongs to the class and is shared by every object; an
 * <em>instance</em> field belongs to each individual object. Here, every Robot
 * shares one counter of how many Robots have been made, while each Robot has its
 * own id and name. Complete the constructor and getters below. Edit only this
 * file.
 *
 * Relevant reading: 2.2. Variables in classes, 2.7. Class (static) methods.
 */
public class Robot {

  /** Shared by all Robots: how many have been created so far. */
  private static int count = 0;

  /** This Robot's own id (0 for the first Robot made, 1 for the next, ...). */
  private final int id;

  /** This Robot's own name. */
  private final String name;

  /**
   * Creates a Robot with the given name, assigns it the next id, and updates the
   * shared count.
   *
   * @param name this Robot's name
   */
  public Robot(String name) {
    this.id = count;
    this.name = name;
    count++;
  }

  /**
   * Returns how many Robots have been created so far.
   *
   * @return the shared Robot count
   */
  public static int getCount() {
    return count;
  }

  /**
   * Returns this Robot's id.
   *
   * @return this Robot's id
   */
  public int getId() {
    return this.id;
  }

  /**
   * Returns this Robot's name.
   *
   * @return this Robot's name
   */
  public String getName() {
    return this.name;
  }
}
