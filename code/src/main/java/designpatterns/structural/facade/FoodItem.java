package designpatterns.structural.facade;

/** Represents a single food item with a name and a price. */
public class FoodItem {

  private final String name;
  private final double price;

  /**
   * Creates a food item with the given name and price.
   *
   * @param it the name of the food item
   * @param price the price of the food item
   */
  public FoodItem(String it, double price) {
    this.name = it;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

  /**
   * Returns a string representation of this food item.
   *
   * @return the name followed by the price
   */
  public String toString() {
    return name + "," + price;
  }
}
