/**
 * Exercise (Chapter 13: Refactoring) — Replace Constructor with Factory Method.
 *
 * Right now callers build a Temperature with {@code new Temperature(celsius)},
 * which is easy to misread: is that number Celsius or Fahrenheit? Applying the
 * "Replace Constructor with Factory Method" refactoring, add two clearly-named
 * static factory methods so the caller's intent is obvious. Complete
 * {@link #fromCelsius(double)} and {@link #fromFahrenheit(double)}. Edit only
 * this file.
 *
 * (Once the factories exist, a common next step is to make the constructor
 * {@code private} so callers must go through them — try it and re-run the tests.)
 *
 * Relevant reading: 13.8. Replace Constructor with Factory Method.
 */
public class Temperature {

  private final double celsius;

  /**
   * Creates a Temperature from a value in degrees Celsius.
   *
   * @param celsius the temperature in degrees Celsius
   */
  public Temperature(double celsius) {
    this.celsius = celsius;
  }

  /**
   * Returns this temperature in degrees Celsius.
   *
   * @return degrees Celsius
   */
  public double getCelsius() {
    return celsius;
  }

  /**
   * Returns this temperature in degrees Fahrenheit.
   *
   * @return degrees Fahrenheit
   */
  public double getFahrenheit() {
    return celsius * 9 / 5 + 32;
  }

  /**
   * Creates a Temperature from a value in degrees Celsius.
   *
   * @param celsius degrees Celsius
   * @return a Temperature representing that value
   */
  public static Temperature fromCelsius(double celsius) {
    // TODO: return a Temperature for this Celsius value.
    return null;
  }

  /**
   * Creates a Temperature from a value in degrees Fahrenheit, converting it to
   * Celsius first.
   *
   * @param fahrenheit degrees Fahrenheit
   * @return a Temperature representing that value
   */
  public static Temperature fromFahrenheit(double fahrenheit) {
    // TODO: convert Fahrenheit to Celsius, (fahrenheit - 32) * 5 / 9, and return
    //       a Temperature for it.
    return null;
  }
}
