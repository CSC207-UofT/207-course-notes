package designpatterns.structural.facade;

/** Represents a food item together with its ordered quantity. */
public class OrderItem {

  private final FoodItem item;
  private final int quantity;

  /**
   * Creates an order item for the given food item and quantity.
   *
   * @param item the food item ordered
   * @param quantity the number of units ordered
   */
  public OrderItem(FoodItem item, int quantity) {
    this.item = item;
    this.quantity = quantity;
  }

  public FoodItem getItem() {
    return item;
  }

  public int getQuantity() {
    return quantity;
  }

  /**
   * Returns a string representation of this order item.
   *
   * @return the item followed by its quantity
   */
  public String toString() {
    return item.toString() + "," + quantity;
  }
}
