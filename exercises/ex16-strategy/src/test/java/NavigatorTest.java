import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Tests for {@link Navigator} and the direction strategies. Do NOT modify this file. */
class NavigatorTest {

  @Test
  void transitDirectionsFormatsCorrectly() {
    assertEquals("Take transit to Vancouver", new TransitDirections().getDirections("Vancouver"));
  }

  @Test
  void navigatorUsesTheDrivingStrategy() {
    Navigator navigator = new Navigator(new DrivingDirections());
    assertEquals("Drive to Toronto", navigator.getDirections("Toronto"));
  }

  @Test
  void navigatorUsesTheTransitStrategy() {
    Navigator navigator = new Navigator(new TransitDirections());
    assertEquals("Take transit to Ottawa", navigator.getDirections("Ottawa"));
  }

  @Test
  void strategyCanBeSwappedAtRuntime() {
    Navigator navigator = new Navigator(new DrivingDirections());
    assertEquals("Drive to Montreal", navigator.getDirections("Montreal"));

    navigator.setDirectionGenerator(new TransitDirections());
    assertEquals("Take transit to Montreal", navigator.getDirections("Montreal"));
  }
}
