import java.util.Locale;

/**
 * Exercise (Chapter 13: Refactoring Techniques) — cleaning up a long method.
 *
 * <p>This exercise works differently from most of the others: <strong>the code
 * below is already correct and {@code OrderSummaryTest} already passes.</strong>
 * Your job is not to make a red test go green — it is to improve the
 * <em>structure</em> of {@link #summarize(String, String[], double[])} without
 * changing what it does. When you are finished, every test in
 * {@code OrderSummaryTest} must <strong>still pass</strong>. That is the whole
 * point of refactoring: behaviour is preserved, design improves.
 *
 * <p>Refactorings to apply (see the {@code // TODO} markers in the body):
 *
 * <ul>
 *   <li><em>Extract Method</em> (13.2) — the report-building block at the end is
 *       a separate job and belongs in its own method; so is the per-item line
 *       formatting.
 *   <li><em>Split Loop</em> (13.5) — one loop currently does two unrelated jobs
 *       (totalling prices and counting premium items). Split it into two loops,
 *       each doing one thing, then consider extracting each into its own method.
 *   <li><em>Slide Statements</em> (13.6) — several accumulator variables are
 *       declared far above the code that uses them. Slide each declaration down
 *       next to its first use so related code sits together.
 *   <li><em>Replace magic numbers with named constants</em> — {@code 0.13},
 *       {@code 200.0}, {@code 0.10} and {@code 100.0} appear as bare literals.
 *       Give each one a {@code private static final} constant with a name that
 *       explains it.
 * </ul>
 *
 * <p>You do not have to do this by hand: IntelliJ's automated Extract Method
 * (Ctrl+Alt+M on Windows/Linux, ⌥⌘M on macOS) will do most of the work — select
 * the statements, invoke it, and name the new method. "Introduce Constant"
 * (Ctrl+Alt+C / ⌥⌘C) handles the magic numbers.
 *
 * <p>Work in small steps and <strong>re-run the tests after every step</strong>.
 * If a step turns the tests red, undo it and try a smaller one. Edit only this
 * file — {@code OrderSummaryTest} is off limits.
 *
 * <p>Relevant reading: 13.2 Extract Method, 13.5 Split Loop, 13.6 Slide
 * Statements.
 */
public class OrderSummary {

  /**
   * Builds a human-readable, multi-line summary of an order.
   *
   * <p>An order gets a 10% discount when its subtotal is more than $200, and tax
   * is then charged on the discounted amount. An item costing $100 or more counts
   * as a "premium" item.
   *
   * @param customer the customer's name
   * @param itemNames the name of each item, in order
   * @param itemPrices the price of each item, in the same order as {@code itemNames}
   * @return the formatted summary, with lines separated by {@code \n}
   */
  public static String summarize(String customer, String[] itemNames, double[] itemPrices) {
    // TODO (Slide Statements, 13.6): these three declarations are a long way from
    //      the code that first uses them. Slide each one down to its first use.
    double subtotal = 0.0;
    int premiumCount = 0;
    double discount = 0.0;

    // TODO (Split Loop, 13.5): this single loop does two unrelated jobs —
    //      accumulating the subtotal and counting premium items. Split it into
    //      two loops, then consider Extract Method (13.2) on each one.
    for (int i = 0; i < itemPrices.length; i++) {
      subtotal += itemPrices[i];
      if (itemPrices[i] >= 100.0) {
        premiumCount++;
      }
    }

    // TODO: replace the magic numbers below with named constants.
    if (subtotal > 200.0) {
      discount = subtotal * 0.10;
    }
    double taxable = subtotal - discount;
    double tax = taxable * 0.13;
    double total = taxable + tax;

    // TODO (Extract Method, 13.2): everything from here down is one job —
    //      formatting the report. Pull it out into its own well-named method
    //      (and the per-item line into a second one).
    StringBuilder report = new StringBuilder();
    report.append("Order summary for ").append(customer).append("\n");
    report.append("----------------------\n");
    for (int i = 0; i < itemNames.length; i++) {
      report.append(String.format(Locale.US, "%s: $%.2f\n", itemNames[i], itemPrices[i]));
    }
    report.append(String.format(Locale.US, "Items: %d\n", itemNames.length));
    report.append(String.format(Locale.US, "Premium items: %d\n", premiumCount));
    report.append(String.format(Locale.US, "Subtotal: $%.2f\n", subtotal));
    report.append(String.format(Locale.US, "Discount: $%.2f\n", discount));
    report.append(String.format(Locale.US, "Tax: $%.2f\n", tax));
    report.append(String.format(Locale.US, "Total: $%.2f", total));
    return report.toString();
  }
}
