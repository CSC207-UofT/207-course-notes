import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link CounterPanel}. Do NOT modify this file.
 *
 * These build the panel and click the button programmatically with
 * {@code doClick()} — no window is shown, so they run without a display.
 */
class CounterPanelTest {

  @Test
  void labelStartsAtZero() {
    assertEquals("Count: 0", new CounterPanel().getLabel().getText());
  }

  @Test
  void oneClickShowsOne() {
    CounterPanel panel = new CounterPanel();
    panel.getButton().doClick();
    assertEquals("Count: 1", panel.getLabel().getText());
  }

  @Test
  void clicksAccumulate() {
    CounterPanel panel = new CounterPanel();
    panel.getButton().doClick();
    panel.getButton().doClick();
    panel.getButton().doClick();
    assertEquals("Count: 3", panel.getLabel().getText());
  }
}
