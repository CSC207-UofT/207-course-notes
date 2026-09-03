import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Tests for {@link Manager}. Do NOT modify this file. */
class ManagerTest {

  @Test
  void managesARegularWorker() {
    Manager manager = new Manager();
    manager.setWorker(new Worker());
    assertEquals("worker is working", manager.manage());
  }

  @Test
  void managesASuperWorker() {
    Manager manager = new Manager();
    manager.setWorker(new SuperWorker());
    assertEquals("super worker is working much more", manager.manage());
  }

  @Test
  void switchingWorkersChangesWhatIsManaged() {
    Manager manager = new Manager();
    manager.setWorker(new Worker());
    assertEquals("worker is working", manager.manage());

    manager.setWorker(new SuperWorker());
    assertEquals("super worker is working much more", manager.manage());
  }

  /**
   * The real payoff of the DIP: once Manager depends on the IWorker abstraction,
   * it works with implementations that did not exist when Manager was written —
   * such as this one, defined here in the test.
   */
  @Test
  void managesAnImplementationItHasNeverSeenBefore() {
    Manager manager = new Manager();
    manager.setWorker(() -> "robot is working tirelessly");
    assertEquals("robot is working tirelessly", manager.manage());
  }
}
