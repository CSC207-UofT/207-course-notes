package cs.toronto.edu.csc207.hello;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Sample test class demonstrating the test folder structure and JUnit 5. */
class HelloWorldTest {

  @Test
  void testGreeting() {
    String greeting = "Hello, World!";
    assertEquals("Hello, World!", greeting);
  }
}
