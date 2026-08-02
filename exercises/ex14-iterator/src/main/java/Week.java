import java.util.Collections;
import java.util.Iterator;

/**
 * Exercise (Chapter 7: Collections) — making a class {@code Iterable}.
 *
 * A class that implements {@code Iterable<E>} can be used in an enhanced
 * for-loop ("for-each"). {@code Week} already declares
 * {@code implements Iterable<String>}, but its {@link #iterator()} method is not
 * finished. Complete it so it yields the seven days in order (Sunday first).
 * Edit only this file.
 *
 * How iteration works: {@code for (String day : week)} calls {@code week.iterator()}
 * once to get an {@code Iterator<String>}, then repeatedly calls {@code hasNext()}
 * and {@code next()} on it.
 *
 * Relevant reading: Chapter 7. Collections.
 */
public class Week implements Iterable<String> {

  private final String[] days = {
    "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
  };

  /**
   * Returns the day at the given index (0 = Sunday ... 6 = Saturday).
   *
   * @param i the index
   * @return the day name at that index
   */
  public String getDay(int i) {
    return days[i];
  }

  @Override
  public Iterator<String> iterator() {
    // TODO: return an Iterator<String> that yields days[0], days[1], ... in
    //       order. The usual approach is a small (nested) class that implements
    //       Iterator<String>:
    //         - hasNext() reports whether any days remain;
    //         - next() returns the next day and advances, or throws
    //           java.util.NoSuchElementException if none remain.
    //       Replace the empty iterator below with an instance of your class.
    return Collections.emptyIterator();
  }

  /** Prints each day of the week, one per line. */
  public static void main(String[] args) {
    Week week = new Week();
    for (String day : week) {
      System.out.println(day);
    }
  }
}
