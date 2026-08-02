/**
 * Exercise (Chapter 9: Design Principles) — the Dependency Inversion Principle.
 *
 * <p>{@code Manager} is the <em>high-level</em> class: managing work is the core
 * logic we care about. {@link Worker} and {@link SuperWorker} are <em>low-level</em>
 * details. As written, {@code Manager} depends directly on the concrete
 * {@code Worker} class, so it only ever manages a plain {@code Worker} — even when
 * you hand it a {@code SuperWorker}. Adding another kind of worker would mean
 * rewriting this class.
 *
 * <p>Apply the DIP so that {@code Manager} depends on the {@link IWorker}
 * abstraction instead:
 *
 * <ol>
 *   <li>change the field's type from {@code Worker} to {@code IWorker}, and</li>
 *   <li>store the worker that was actually passed in, rather than creating one
 *       here.</li>
 * </ol>
 *
 * <p>Notice what you are <em>not</em> asked to do: you do not need to add a
 * second field, a second setter, or an {@code if} statement per worker type. That
 * is the payoff — once {@code Manager} depends on the abstraction, it works with
 * <em>any</em> implementation of {@code IWorker}, including ones that do not exist
 * yet. Edit only this file.
 *
 * <p>Relevant reading: 9.2.5. DIP (see the "Example: managers and workers"
 * section, which this exercise is based on).
 */
public class Manager {

  // TODO: the type of this field is the problem — it names a concrete class
  //       rather than the abstraction. Change it to IWorker.
  private Worker worker;

  /**
   * Sets the worker that this manager manages.
   *
   * @param worker the worker to manage
   */
  public void setWorker(IWorker worker) {
    // TODO: store the worker that was passed in, instead of ignoring it and
    //       hard-coding a new Worker.
    this.worker = new Worker();
  }

  /**
   * Manages the current worker by telling them to do their work.
   *
   * @return a description of the work that was done
   */
  public String manage() {
    return worker.work();
  }
}
