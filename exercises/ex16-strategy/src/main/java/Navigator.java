/**
 * Exercise (Chapter 12: Design Patterns) — the Strategy pattern.
 *
 * {@code Navigator} is the <em>Context</em> (the chapter calls it {@code Map};
 * we use {@code Navigator} to avoid clashing with {@code java.util.Map}). It
 * holds a {@link DirectionGenerator} strategy and delegates to it, so the same
 * Navigator can produce driving or transit directions just by swapping the
 * strategy — without changing this class.
 *
 * Complete the constructor, the setter, and {@code getDirections} so they store
 * and use the strategy. Edit only this file (and {@link TransitDirections}).
 *
 * Relevant reading: 12.3.1. Strategy.
 */
public class Navigator {

  private DirectionGenerator generator;

  /**
   * Creates a Navigator that uses the given strategy.
   *
   * @param generator the direction-generating strategy to start with
   */
  public Navigator(DirectionGenerator generator) {
    // TODO: store the strategy in the field.
  }

  /**
   * Switches to a different strategy.
   *
   * @param generator the new direction-generating strategy
   */
  public void setDirectionGenerator(DirectionGenerator generator) {
    // TODO: replace the current strategy.
  }

  /**
   * Returns directions to {@code destination} by delegating to the current
   * strategy.
   *
   * @param destination where to go
   * @return the directions produced by the current strategy
   */
  public String getDirections(String destination) {
    // TODO: ask the current strategy for the directions and return them.
    return "";
  }
}
