import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Exercise (Chapter 14: Regular Expressions) — extraction with Pattern and Matcher.
 *
 * <p>{@code Validators} covered the <em>validation</em> category of regex tasks:
 * "does this whole string match?", answered with {@code String.matches}. This
 * exercise covers the other category from 14.7, <em>extraction</em>: pulling the
 * pieces you care about out of a larger body of text. {@code String.matches}
 * cannot do that — you need {@link Pattern} and {@link Matcher} (14.6).
 *
 * <p>The shape of an extraction loop is always the same:
 *
 * <pre>{@code
 * Matcher matcher = SOME_PATTERN.matcher(text);
 * while (matcher.find()) {
 *   results.add(matcher.group());   // group()  = the whole match
 *   // matcher.group(1) = the text captured by the first (...) group
 * }
 * }</pre>
 *
 * <p>Notice that {@link #COURSE_CODE} below is a {@code private static final
 * Pattern}, compiled once when the class is loaded. Compiling a regular
 * expression is real work: the pattern string has to be parsed and turned into a
 * matching machine. {@code String.matches} and {@code Pattern.compile} inside a
 * method redo that work on every single call. Compiling once and reusing the
 * {@code Pattern} does it once for the life of the program. ({@code Pattern} is
 * immutable and safe to share; {@code Matcher} holds the state of one particular
 * search, so you create a fresh one per input.)
 *
 * <p>Complete each method below. Edit only this file.
 *
 * <p>Relevant reading: 14.6 Regex in Java, and 14.7 What Can We Do with Regular
 * Expressions? (which describes the extraction/validation split).
 */
public class Extractor {

  /**
   * Matches a UofT-style course code: three letters, three digits, then a
   * campus/session suffix such as {@code H1F} — a letter, a digit, and a letter.
   * The parentheses make the three digits a <em>capturing group</em>, which
   * {@link #findCourseNumbers(String)} can pull out with {@code group(1)}.
   */
  private static final Pattern COURSE_CODE = Pattern.compile("[A-Z]{3}(\\d{3})[A-Z]\\d[A-Z]");

  /**
   * Matches a simple email address: one or more "local" characters (letters,
   * digits, or any of {@code . _ % + -}), an {@code @}, a domain of
   * letters/digits/{@code . -}, then a {@code .} and a top-level domain of at
   * least two letters. This is the same shape as {@code Validators.isEmail}.
   */
  private static final Pattern EMAIL =
      Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}");

  /** The text used to replace each email address found by {@link #maskEmails(String)}. */
  private static final String MASK = "***";

  /**
   * Finds every course code appearing anywhere in {@code text}, in the order they
   * appear. For example, the text {@code "Take CSC207H1F before CSC236H1S."}
   * yields {@code ["CSC207H1F", "CSC236H1S"]}.
   *
   * @param text the text to search; may contain any number of course codes
   * @return the course codes found, or an empty list if there are none (never null)
   */
  public static List<String> findCourseCodes(String text) {
    List<String> results = new ArrayList<>();
    // TODO: get a Matcher for text from COURSE_CODE, then loop with find(),
    //       adding matcher.group() to results each time.
    return results;
  }

  /**
   * Finds just the three-digit number of every course code in {@code text}, in the
   * order they appear. For example, {@code "Take CSC207H1F before CSC236H1S."}
   * yields {@code ["207", "236"]}.
   *
   * @param text the text to search
   * @return the course numbers found, or an empty list if there are none (never null)
   */
  public static List<String> findCourseNumbers(String text) {
    List<String> results = new ArrayList<>();
    // TODO: same loop as above, but add matcher.group(1) — the text captured by
    //       the parenthesised (\d{3}) group — instead of the whole match.
    return results;
  }

  /**
   * Returns {@code text} with every email address replaced by {@code ***}. Text
   * that is not an email address is left exactly as it was, and text containing no
   * email addresses is returned unchanged.
   *
   * @param text the text to mask
   * @return the text with each email address replaced by {@code ***}
   */
  public static String maskEmails(String text) {
    // TODO: get a Matcher for text from EMAIL and return matcher.replaceAll(MASK).
    return text;
  }
}
