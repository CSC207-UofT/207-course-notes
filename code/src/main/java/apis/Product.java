package apis;

/**
 * A food product, as described by the Open Food Facts database.
 *
 * <p>This is an <em>entity</em>: it holds the data our program cares about and knows nothing about
 * where that data came from. The job of turning an API response into one of these belongs to {@link
 * OpenFoodFactsClient}.
 */
public class Product {

  private final String barcode;
  private final String name;
  private final String brands;
  private final String nutriScoreGrade;
  private final double energyKcalPer100g;
  private final double sugarsPer100g;

  /**
   * Creates a product.
   *
   * @param barcode the product's barcode (its Open Food Facts identifier)
   * @param name the product name
   * @param brands the brand(s), as a comma-separated string
   * @param nutriScoreGrade the Nutri-Score grade, "a" (best) through "e" (worst), or "?" if unknown
   * @param energyKcalPer100g energy in kcal per 100g, or NaN if unknown
   * @param sugarsPer100g sugars in grams per 100g, or NaN if unknown
   */
  public Product(
      String barcode,
      String name,
      String brands,
      String nutriScoreGrade,
      double energyKcalPer100g,
      double sugarsPer100g) {
    this.barcode = barcode;
    this.name = name;
    this.brands = brands;
    this.nutriScoreGrade = nutriScoreGrade;
    this.energyKcalPer100g = energyKcalPer100g;
    this.sugarsPer100g = sugarsPer100g;
  }

  /**
   * Returns this product's barcode.
   *
   * @return the barcode
   */
  public String getBarcode() {
    return barcode;
  }

  /**
   * Returns this product's name.
   *
   * @return the product name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns this product's brand(s).
   *
   * @return the brands, as a comma-separated string
   */
  public String getBrands() {
    return brands;
  }

  /**
   * Returns this product's Nutri-Score grade.
   *
   * @return "a" through "e", or "?" if the database does not have one
   */
  public String getNutriScoreGrade() {
    return nutriScoreGrade;
  }

  /**
   * Returns the energy per 100g in kilocalories.
   *
   * @return kcal per 100g, or NaN if unknown
   */
  public double getEnergyKcalPer100g() {
    return energyKcalPer100g;
  }

  /**
   * Returns the sugar content per 100g.
   *
   * @return grams of sugar per 100g, or NaN if unknown
   */
  public double getSugarsPer100g() {
    return sugarsPer100g;
  }

  @Override
  public String toString() {
    return String.format(
        "%s (%s) — Nutri-Score %s, %.0f kcal/100g, %.1fg sugar/100g",
        name, brands, nutriScoreGrade.toUpperCase(), energyKcalPer100g, sugarsPer100g);
  }
}
