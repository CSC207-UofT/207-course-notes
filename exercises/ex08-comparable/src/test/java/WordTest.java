import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Tests for {@link Word}. Do NOT modify this file. */
class WordTest {

  @Test
  void shorterWordIsLessThanLonger() {
    assertTrue(new Word("hi").compareTo(new Word("hello")) < 0);
  }

  @Test
  void longerWordIsGreaterThanShorter() {
    assertTrue(new Word("hello").compareTo(new Word("hi")) > 0);
  }

  @Test
  void sameLengthComparesEqual() {
    assertEquals(0, new Word("cat").compareTo(new Word("dog")));
  }

  @Test
  void collectionsSortOrdersByLength() {
    List<Word> words =
        new ArrayList<>(Arrays.asList(new Word("ccc"), new Word("a"), new Word("bb")));
    Collections.sort(words);
    assertEquals("a", words.get(0).getText());
    assertEquals("bb", words.get(1).getText());
    assertEquals("ccc", words.get(2).getText());
  }
}
