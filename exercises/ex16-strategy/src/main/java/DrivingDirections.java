/**
 * A <em>concrete strategy</em>: driving directions. This one is completed for
 * you as a worked example — model {@code TransitDirections} on it.
 */
public class DrivingDirections implements DirectionGenerator {

  @Override
  public String getDirections(String destination) {
    return "Drive to " + destination;
  }
}
