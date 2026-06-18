import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link Rectangle}. These already pass — the code is correct, just
 * messy. Do NOT modify this file. Your job is to clean up the STYLE of
 * Rectangle.java (and keep these tests green).
 */
class RectangleTest {

    @Test
    void areaMultipliesWidthByHeight() {
        assertEquals(12.0, new Rectangle(3.0, 4.0).area());
    }

    @Test
    void scaleGrowsBothDimensions() {
        Rectangle r = new Rectangle(2.0, 5.0);
        r.scale(3.0);
        assertEquals(90.0, r.area());
    }

    @Test
    void isLargerThanComparesAreas() {
        Rectangle big = new Rectangle(10.0, 10.0);
        Rectangle small = new Rectangle(1.0, 2.0);
        assertTrue(big.isLargerThan(small));
        assertFalse(small.isLargerThan(big));
    }
}
