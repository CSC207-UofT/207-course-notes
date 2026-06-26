import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** Tests for {@link Point}. Do NOT modify this file. */
class PointTest {

  @Test
  void toStringFormatsCoordinates() {
    assertEquals("(3, 4)", new Point(3, 4).toString());
  }

  @Test
  void equalForSameCoordinates() {
    assertTrue(new Point(1, 2).equals(new Point(1, 2)));
  }

  @Test
  void notEqualForDifferentCoordinates() {
    assertFalse(new Point(1, 2).equals(new Point(2, 1)));
  }

  @Test
  void notEqualToNullOrOtherTypes() {
    Point p = new Point(1, 2);
    assertFalse(p.equals(null));
    assertFalse(p.equals("(1, 2)"));
  }

  @Test
  void equalPointsShareHashCode() {
    assertEquals(new Point(5, 6).hashCode(), new Point(5, 6).hashCode());
  }

  @Test
  void differentPointsDifferInHashCode() {
    assertNotEquals(new Point(1, 2).hashCode(), new Point(3, 4).hashCode());
  }
}
