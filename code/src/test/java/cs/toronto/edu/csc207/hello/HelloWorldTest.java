package cs.toronto.edu.csc207.hello;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Sample test class demonstrating the test folder structure and JUnit 5.
 */
class HelloWorldTest {

  @Test
  void testGreeting() {
    String greeting = "Hello, World!";
    assertEquals("Hello, World!", greeting);
  }
}
