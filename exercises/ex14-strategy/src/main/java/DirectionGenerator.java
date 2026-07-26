/**
 * The <em>Strategy</em> interface for the Strategy pattern (Chapter 12.3.1): an
 * interchangeable algorithm for generating directions to a destination.
 *
 * <p>This interface is complete — you do not need to change it.
 */
public interface DirectionGenerator {

  /**
   * Returns directions to the given destination.
   *
   * @param destination where to go
   * @return a human-readable set of directions
   */
  String getDirections(String destination);
}
