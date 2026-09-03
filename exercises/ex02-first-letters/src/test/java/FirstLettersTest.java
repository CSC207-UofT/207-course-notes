import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link FirstLetters}. Do NOT modify this file — make these pass by
 * completing FirstLetters.firstLetters.
 */
class FirstLettersTest {

    @Test
    void acronymFromSevenWords() {
        assertEquals("ILOVEUT",
                FirstLetters.firstLetters("Idol Long Oolong Vertical Europe University Toyota"));
    }

    @Test
    void singleLetterWords() {
        assertEquals("ABCDEFG", FirstLetters.firstLetters("A B C D E F G"));
    }

    @Test
    void anotherSentence() {
        assertEquals("TFLANTI",
                FirstLetters.firstLetters("The First Letters Are Not That Interesting"));
    }

    @Test
    void oneWord() {
        assertEquals("H", FirstLetters.firstLetters("Hello"));
    }

    @Test
    void twoWords() {
        assertEquals("GM", FirstLetters.firstLetters("Good Morning"));
    }
}
