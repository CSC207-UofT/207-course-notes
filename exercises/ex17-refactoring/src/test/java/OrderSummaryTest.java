import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link OrderSummary}. Do NOT modify this file.
 *
 * <p>These tests pass before you start. They pin down the observable behaviour of
 * {@code summarize} — the exact string it returns — and say nothing about how it
 * is structured internally. That is deliberate: you are free to extract methods,
 * split loops, and introduce constants however you like, as long as every test
 * here still passes afterwards.
 */
class OrderSummaryTest {

  @Test
  void emptyOrder() {
    String expected =
        "Order summary for Alice\n"
            + "----------------------\n"
            + "Items: 0\n"
            + "Premium items: 0\n"
            + "Subtotal: $0.00\n"
            + "Discount: $0.00\n"
            + "Tax: $0.00\n"
            + "Total: $0.00";
    assertEquals(expected, OrderSummary.summarize("Alice", new String[] {}, new double[] {}));
  }

  @Test
  void singleItem() {
    String expected =
        "Order summary for Bob\n"
            + "----------------------\n"
            + "Widget: $10.00\n"
            + "Items: 1\n"
            + "Premium items: 0\n"
            + "Subtotal: $10.00\n"
            + "Discount: $0.00\n"
            + "Tax: $1.30\n"
            + "Total: $11.30";
    assertEquals(
        expected,
        OrderSummary.summarize("Bob", new String[] {"Widget"}, new double[] {10.0}));
  }

  @Test
  void severalItemsBelowTheDiscountThreshold() {
    String expected =
        "Order summary for Cai\n"
            + "----------------------\n"
            + "Widget: $10.00\n"
            + "Gadget: $20.00\n"
            + "Doodad: $30.00\n"
            + "Items: 3\n"
            + "Premium items: 0\n"
            + "Subtotal: $60.00\n"
            + "Discount: $0.00\n"
            + "Tax: $7.80\n"
            + "Total: $67.80";
    assertEquals(
        expected,
        OrderSummary.summarize(
            "Cai",
            new String[] {"Widget", "Gadget", "Doodad"},
            new double[] {10.0, 20.0, 30.0}));
  }

  @Test
  void orderThatCrossesTheDiscountThreshold() {
    String expected =
        "Order summary for Dana\n"
            + "----------------------\n"
            + "Monitor: $100.00\n"
            + "Keyboard: $150.00\n"
            + "Items: 2\n"
            + "Premium items: 2\n"
            + "Subtotal: $250.00\n"
            + "Discount: $25.00\n"
            + "Tax: $29.25\n"
            + "Total: $254.25";
    assertEquals(
        expected,
        OrderSummary.summarize(
            "Dana",
            new String[] {"Monitor", "Keyboard"},
            new double[] {100.0, 150.0}));
  }

  @Test
  void discountAppliesOnlyAboveTheThresholdNotAtIt() {
    String atThreshold =
        OrderSummary.summarize("Eve", new String[] {"Chair"}, new double[] {200.0});
    assertEquals(
        "Order summary for Eve\n"
            + "----------------------\n"
            + "Chair: $200.00\n"
            + "Items: 1\n"
            + "Premium items: 1\n"
            + "Subtotal: $200.00\n"
            + "Discount: $0.00\n"
            + "Tax: $26.00\n"
            + "Total: $226.00",
        atThreshold);
  }
}
