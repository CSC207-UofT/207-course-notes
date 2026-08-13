/**
 * Exercise (Chapter 14: Regular Expressions) — writing patterns and matching.
 *
 * Complete each method so it returns true exactly when the input matches the
 * described pattern. Use String's {@code matches(regex)} method, which returns
 * true iff the <em>whole</em> string matches the regular expression. Edit only
 * this file.
 *
 * Relevant reading: 14.6. Regex in Java (and 14.4 for identifier patterns).
 */
public class Validators {

  /**
   * Returns whether {@code input} looks like an email address: one or more
   * "local" characters (letters, digits, or any of {@code . _ % + -}), then an
   * {@code @}, then a domain of letters/digits/{@code . -}, then a {@code .} and
   * a top-level domain of at least two letters.
   *
   * @param input the string to test
   * @return true iff input is a valid email address
   */
  public static boolean isEmail(String input) {
    // TODO: return input.matches("...") with an appropriate pattern.
    return false;
  }

  /**
   * Returns whether {@code input} is a phone number of the form
   * {@code NNN-NNN-NNNN} (three digits, a dash, three digits, a dash, four
   * digits).
   *
   * @param input the string to test
   * @return true iff input matches the phone-number pattern
   */
  public static boolean isPhoneNumber(String input) {
    // TODO
    return false;
  }

  /**
   * Returns whether {@code input} is a legal Java variable name: it starts with
   * a letter, underscore, or dollar sign, followed by any number of letters,
   * digits, underscores, or dollar signs.
   *
   * @param input the string to test
   * @return true iff input is a valid Java identifier
   */
  public static boolean isJavaVariableName(String input) {
    // TODO
    return false;
  }
}
