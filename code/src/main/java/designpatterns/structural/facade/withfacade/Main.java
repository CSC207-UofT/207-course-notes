package designpatterns.structural.facade.withfacade;

/** Demonstrates the Facade pattern by issuing a bill through a facade. */
public class Main {

  /**
   * Runs the facade demonstration.
   *
   * @param args command line arguments (unused)
   */
  public static void main(String[] args) {

    /*
     * Design Pattern: Facade
     *
     * @author: Mahsa Sadi
     * @since: 2020 - 08 - 31
     */

    // the bill facade hides all details of the underlying subsystems from the client!
    BillFacade facade = new BillFacade();
    /*
     * Issuing a bill is composed of several steps and requires interaction
     * between several objects that are declared inside the facade.
     * However, all these complexities are hidden from the client.
     * The facade encapsulates all steps and all the coordination.
     */
    facade.issueBill();
  }
}
