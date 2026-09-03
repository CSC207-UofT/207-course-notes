import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link OddSum}. Do NOT modify this file — your job is to make
 * these tests pass by completing OddSum.oddSum.
 *
 * Run all tests with the green ▶ in the gutter, or from the repo root with:
 *   mvn -P exercises test -pl exercises/ex01-odd-sum
 */
class OddSumTest {

    @Test
    void sumsValuesAtOddIndices() {
        assertEquals(60, OddSum.oddSum(new int[]{10, 20, 30, 40, 50}));
    }

    @Test
    void emptyArrayIsZero() {
        assertEquals(0, OddSum.oddSum(new int[]{}));
    }

    @Test
    void singleElementHasNoOddIndices() {
        assertEquals(0, OddSum.oddSum(new int[]{42}));
    }

    @Test
    void handlesNegativeNumbers() {
        // odd indices hold -5 and -10
        assertEquals(-15, OddSum.oddSum(new int[]{1, -5, 2, -10}));
    }
}
