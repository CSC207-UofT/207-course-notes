import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Tests for {@link Temperature}. Do NOT modify this file. */
class TemperatureTest {

  private static final double DELTA = 1e-9;

  @Test
  void fromCelsiusKeepsTheCelsiusValue() {
    assertEquals(100.0, Temperature.fromCelsius(100).getCelsius(), DELTA);
  }

  @Test
  void fromCelsiusConvertsToFahrenheit() {
    assertEquals(32.0, Temperature.fromCelsius(0).getFahrenheit(), DELTA);
    assertEquals(212.0, Temperature.fromCelsius(100).getFahrenheit(), DELTA);
  }

  @Test
  void fromFahrenheitConvertsToCelsius() {
    assertEquals(0.0, Temperature.fromFahrenheit(32).getCelsius(), DELTA);
    assertEquals(100.0, Temperature.fromFahrenheit(212).getCelsius(), DELTA);
  }

  @Test
  void fromFahrenheitRoundTrips() {
    assertEquals(98.6, Temperature.fromFahrenheit(98.6).getFahrenheit(), DELTA);
  }
}
