package apis;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * Fetches two products from Open Food Facts and compares them.
 *
 * <p>Run this file to see a real API call in action. It needs an internet connection but no API key
 * or sign-up. Try swapping in the barcode of something in your own kitchen — it is printed on the
 * package, right underneath the bars.
 */
public class CompareProducts {

  /** Nutella, a well-known product that is definitely in the database. */
  private static final String NUTELLA = "3017620422003";

  /** Coca-Cola, for comparison. */
  private static final String COCA_COLA = "5449000000996";

  /**
   * Fetches two products and prints a comparison of them.
   *
   * @param args command-line arguments; optionally two barcodes to compare
   */
  public static void main(String[] args) {
    String first = args.length > 0 ? args[0] : NUTELLA;
    String second = args.length > 1 ? args[1] : COCA_COLA;

    OpenFoodFactsClient client = new OpenFoodFactsClient();
    try {
      Product a = client.fetchByBarcode(first);
      Product b = client.fetchByBarcode(second);

      System.out.println(a);
      System.out.println(b);
      System.out.println();
      System.out.println(compareSugar(a, b));
    } catch (IOException e) {
      // A network problem, or a barcode that isn't in the database.
      System.out.println("Could not fetch the products: " + e.getMessage());
    }

    showChocolateRanking(client);
  }

  /**
   * Fetches a few chocolate products and prints them from most to least sugary.
   *
   * <p>The search endpoint returns many products at once, so it is a good place to practise working
   * with a JSON array. Note that it is <strong>rate limited much more tightly</strong> than the
   * single-product endpoint: call it a few times in quick succession and it will start answering
   * {@code 503} instead of data. That is normal, and it is why this method catches its own errors
   * rather than bringing down the whole program — a lesson worth remembering whenever your code
   * depends on someone else's service.
   *
   * @param client the client to search with
   */
  private static void showChocolateRanking(OpenFoodFactsClient client) {
    System.out.println();
    System.out.println("Some chocolate products, most sugary first:");
    try {
      List<Product> chocolate = client.searchByCategory("chocolate", 5);
      chocolate.sort(Comparator.comparingDouble(Product::getSugarsPer100g).reversed());
      for (Product product : chocolate) {
        System.out.println("  " + product);
      }
    } catch (IOException e) {
      System.out.println("  (search is busy right now: " + e.getMessage() + ")");
    }
  }

  /**
   * Returns a sentence saying which of the two products has more sugar per 100g.
   *
   * @param a the first product
   * @param b the second product
   * @return a human-readable comparison
   */
  public static String compareSugar(Product a, Product b) {
    if (Double.isNaN(a.getSugarsPer100g()) || Double.isNaN(b.getSugarsPer100g())) {
      return "Sugar content is not recorded for both products, so they cannot be compared.";
    }
    if (a.getSugarsPer100g() == b.getSugarsPer100g()) {
      return String.format(
          "%s and %s have the same sugar content (%.1fg per 100g).",
          a.getName(), b.getName(), a.getSugarsPer100g());
    }
    Product sweeter = a.getSugarsPer100g() > b.getSugarsPer100g() ? a : b;
    Product other = sweeter == a ? b : a;
    return String.format(
        "%s has more sugar than %s: %.1fg vs %.1fg per 100g.",
        sweeter.getName(), other.getName(), sweeter.getSugarsPer100g(), other.getSugarsPer100g());
  }
}
