/**
 * Exercise (Chapter 5: Java Gotchas and Subtleties) — shadowing, array copying,
 * and autoboxing.
 *
 * Each method below has a subtle bug of the kind described in this chapter. Fix
 * all three so the tests pass. Edit only this file.
 *
 * Relevant reading: 5.1 Shadowing, 5.2 Array Copy, 5.3 Autoboxing.
 */
public class Gotchas {

  private String name = "";

  /**
   * Returns this object's name.
   *
   * @return the current name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets this object's name (Chapter 5.1: shadowing). The parameter {@code name}
   * shadows the field {@code name}, so writing {@code name = name} just assigns
   * the parameter to itself and leaves the field unchanged.
   *
   * @param name the new name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns an independent deep copy of a 2-D array (Chapter 5.2: array copy).
   * {@code grid.clone()} copies only the outer array — the inner arrays are
   * shared with the original, so modifying the copy would modify the original.
   *
   * @param grid a 2-D array
   * @return a copy whose inner arrays are also copies (nothing shared with grid)
   */
  public static int[][] deepCopy(int[][] grid) {
    int[][] new_list = new int[grid.length][];

    for (int i = 0; i < grid.length; i++){
      new_list[i] = grid[i].clone();
    }

    return new_list;
  }

  /**
   * Returns whether two Integers represent the same int value (Chapter 5.3:
   * autoboxing). Using {@code ==} compares object references, which is only
   * reliable for small cached values — for larger values, two equal Integers
   * can be different objects.
   *
   * @param a the first value
   * @param b the second value
   * @return true iff a and b hold the same int value
   */
  public static boolean sameValue(Integer a, Integer b) {
    int aValue = a;
    int bValue = b;
    return aValue == bValue;
  }
}
