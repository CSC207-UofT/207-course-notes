import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Tests for {@link Week}. Do NOT modify this file. */
class WeekTest {

  @Test
  void forEachVisitsAllSevenDays() {
    int count = 0;
    for (String day : new Week()) {
      count++;
    }
    assertEquals(7, count);
  }

  @Test
  void iteratorYieldsDaysInOrder() {
    Week week = new Week();
    Iterator<String> it = week.iterator();
    for (int i = 0; i < 7; i++) {
      assertTrue(it.hasNext());
      assertEquals(week.getDay(i), it.next());
    }
  }

  @Test
  void firstDayIsSunday() {
    assertEquals("Sunday", new Week().iterator().next());
  }

  @Test
  void hasNextIsFalseAfterLastDay() {
    Iterator<String> it = new Week().iterator();
    for (int i = 0; i < 7; i++) {
      it.next();
    }
    assertFalse(it.hasNext());
  }

  @Test
  void nextThrowsAfterLastDay() {
    Iterator<String> it = new Week().iterator();
    for (int i = 0; i < 7; i++) {
      it.next();
    }
    assertThrows(NoSuchElementException.class, it::next);
  }
}
