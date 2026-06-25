import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Tests for {@link Robot}. Do NOT modify this file.
 *
 * Because the Robot count is shared (static) and keeps growing, these tests
 * check it relative to its value before each step rather than against fixed
 * numbers.
 */
class RobotTest {

  @Test
  void creatingARobotIncreasesTheCount() {
    int before = Robot.getCount();
    new Robot("R2-D2");
    assertEquals(before + 1, Robot.getCount());
  }

  @Test
  void eachRobotGetsTheNextId() {
    int before = Robot.getCount();
    Robot first = new Robot("first");
    Robot second = new Robot("second");
    assertEquals(before, first.getId());
    assertEquals(before + 1, second.getId());
  }

  @Test
  void idsAreUnique() {
    Robot a = new Robot("a");
    Robot b = new Robot("b");
    assertNotEquals(a.getId(), b.getId());
  }

  @Test
  void getNameReturnsTheConstructorArgument() {
    assertEquals("WALL-E", new Robot("WALL-E").getName());
  }
}
