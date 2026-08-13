import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Tests for {@link Validators}. Do NOT modify this file. */
class ValidatorsTest {

  @Test
  void acceptsValidEmails() {
    assertTrue(Validators.isEmail("user@example.com"));
    assertTrue(Validators.isEmail("a.b_c+tag@sub.domain.org"));
  }

  @Test
  void rejectsInvalidEmails() {
    assertFalse(Validators.isEmail("invalid-email"));
    assertFalse(Validators.isEmail("no-at-sign.com"));
    assertFalse(Validators.isEmail("user@nodot"));
  }

  @Test
  void acceptsValidPhoneNumbers() {
    assertTrue(Validators.isPhoneNumber("123-456-7890"));
  }

  @Test
  void rejectsInvalidPhoneNumbers() {
    assertFalse(Validators.isPhoneNumber("1234567890"));
    assertFalse(Validators.isPhoneNumber("12-345-6789"));
    assertFalse(Validators.isPhoneNumber("123-456-789"));
  }

  @Test
  void acceptsValidVariableNames() {
    assertTrue(Validators.isJavaVariableName("count"));
    assertTrue(Validators.isJavaVariableName("_private"));
    assertTrue(Validators.isJavaVariableName("$value"));
    assertTrue(Validators.isJavaVariableName("camelCase123"));
  }

  @Test
  void rejectsInvalidVariableNames() {
    assertFalse(Validators.isJavaVariableName("123abc"));
    assertFalse(Validators.isJavaVariableName("has space"));
    assertFalse(Validators.isJavaVariableName("has-dash"));
    assertFalse(Validators.isJavaVariableName(""));
  }
}
