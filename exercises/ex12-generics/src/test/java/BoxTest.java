import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Tests for {@link Box}. Do NOT modify this file. */
class BoxTest {

  @Test
  void getReturnsWhatWasSet() {
    Box<String> box = new Box<>();
    box.set("hello");
    assertEquals("hello", box.get());
  }

  @Test
  void worksForAnyType() {
    Box<Integer> box = new Box<>();
    box.set(42);
    assertEquals(Integer.valueOf(42), box.get());
  }

  @Test
  void newBoxIsEmpty() {
    assertTrue(new Box<String>().isEmpty());
  }

  @Test
  void boxWithAnItemIsNotEmpty() {
    Box<String> box = new Box<>();
    box.set("x");
    assertFalse(box.isEmpty());
  }

  @Test
  void maxReturnsTheLargerValue() {
    assertEquals(7, Box.max(3, 7).intValue());
    assertEquals(7, Box.max(7, 3).intValue());
  }

  @Test
  void maxWorksForStrings() {
    assertEquals("banana", Box.max("apple", "banana"));
  }
}
