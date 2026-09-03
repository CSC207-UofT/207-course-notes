/**
 * A "super worker": a second low-level implementation of {@link IWorker}, added
 * after the company restructured.
 *
 * <p>This class is complete — you do not need to change it.
 */
public class SuperWorker implements IWorker {

  @Override
  public String work() {
    return "super worker is working much more";
  }
}
