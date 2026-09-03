import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * Tests for {@link Aliasing}. Do NOT modify this file — make these pass by
 * completing addInPlace and addCopy.
 */
class AliasingTest {

    @Test
    void addInPlaceModifiesCallersArray() {
        int[] data = {1, 2, 3};
        Aliasing.addInPlace(data, 10);
        assertArrayEquals(new int[]{11, 12, 13}, data);
    }

    @Test
    void addInPlaceIsVisibleThroughAnAlias() {
        int[] data = {0, 0};
        int[] alias = data;            // alias refers to the same array
        Aliasing.addInPlace(data, 5);
        assertArrayEquals(new int[]{5, 5}, alias);   // the alias sees it too
    }

    @Test
    void addCopyProducesCorrectValues() {
        assertArrayEquals(new int[]{11, 12, 13}, Aliasing.addCopy(new int[]{1, 2, 3}, 10));
    }

    @Test
    void addCopyDoesNotModifyOriginal() {
        int[] original = {1, 2, 3};
        Aliasing.addCopy(original, 10);
        assertArrayEquals(new int[]{1, 2, 3}, original);
    }

    @Test
    void addCopyReturnsANewArray() {
        int[] original = {1, 2, 3};
        assertNotSame(original, Aliasing.addCopy(original, 10));
    }
}
