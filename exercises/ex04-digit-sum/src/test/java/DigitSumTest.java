import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link DigitSum}. Do NOT modify this file — make these pass by
 * completing DigitSum.digitSum.
 */
class DigitSumTest {

    @Test
    void zeroIsZero() {
        assertEquals(0, DigitSum.digitSum(0));
    }

    @Test
    void singleDigit() {
        assertEquals(7, DigitSum.digitSum(7));
    }

    @Test
    void multipleDigits() {
        assertEquals(6, DigitSum.digitSum(123));
    }

    @Test
    void allNines() {
        assertEquals(45, DigitSum.digitSum(99999));
    }

    @Test
    void negativeIgnoresSign() {
        assertEquals(6, DigitSum.digitSum(-123));
    }
}
