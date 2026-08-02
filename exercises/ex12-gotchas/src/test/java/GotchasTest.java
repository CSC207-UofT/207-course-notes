import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Tests for {@link Gotchas}. Do NOT modify this file. */
class GotchasTest {

  @Test
  void setNameUpdatesTheField() {
    Gotchas g = new Gotchas();
    g.setName("Ada");
    assertEquals("Ada", g.getName());
  }

  @Test
  void deepCopyLeavesOriginalUnchanged() {
    int[][] original = {{1, 2}, {3, 4}};
    int[][] copy = Gotchas.deepCopy(original);
    copy[0][0] = 99;
    assertEquals(1, original[0][0]);
  }

  @Test
  void deepCopyHasEqualButSeparateContents() {
    int[][] original = {{1, 2}, {3, 4}};
    int[][] copy = Gotchas.deepCopy(original);
    assertArrayEquals(original, copy); // same values ...
    assertNotSame(original[0], copy[0]); // ... but the inner arrays are copies
  }

  @Test
  void sameValueForLargeEqualIntegers() {
    assertTrue(Gotchas.sameValue(1000, 1000)); // 1000 is outside the Integer cache
  }

  @Test
  void sameValueDistinguishesDifferentValues() {
    assertFalse(Gotchas.sameValue(5, 6));
  }
}
