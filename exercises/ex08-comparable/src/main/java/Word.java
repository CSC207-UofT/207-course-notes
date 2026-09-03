/**
 * Exercise (Chapter 2: Classes) — ordering objects with {@code Comparable}.
 *
 * Implementing {@code Comparable<Word>} lets Java sort and compare Words for you
 * (e.g. with {@code Collections.sort}). The class header already declares
 * {@code implements Comparable<Word>}; your job is to complete {@link
 * #compareTo(Word)} so Words are ordered by their length. Edit only this file.
 *
 * Relevant reading: 2.5. Overloading / comparing objects.
 */
public class Word implements Comparable<Word> {

  private final String text;

  /**
   * Creates a word.
   *
   * @param text the word's text
   */
  public Word(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  /**
   * Compares this word with {@code other} by length. Returns a negative number
   * if this word is shorter than {@code other}, a positive number if it is
   * longer, and 0 if they have the same length.
   *
   * @param other the word to compare with
   * @return negative, zero, or positive as this word is shorter, the same
   *     length, or longer than other
   */
  @Override
  public int compareTo(Word other) {
    // TODO: String has a .length() method. The difference of the two lengths is
    //       already negative / zero / positive in the right cases.
    return 0;
  }
}
