/**
 * The abstraction that {@link Manager} should depend on.
 *
 * <p>This interface is complete — you do not need to change it.
 */
public interface IWorker {

  /**
   * Does this worker's work.
   *
   * @return a description of the work that was done
   */
  String work();
}
