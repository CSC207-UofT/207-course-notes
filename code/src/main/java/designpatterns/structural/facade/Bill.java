package designpatterns.structural.facade;

import java.util.ArrayList;
import java.util.List;

/** Represents a bill holding an identifier, its order items and a total price. */
public class Bill {

  private final int id;
  private final List<OrderItem> orderItems = new ArrayList<>();
  private double totalPrice;

  /**
   * Creates a bill with the given identifier and a zero total.
   *
   * @param id the identifier of the bill
   */
  public Bill(int id) {
    this.id = id;
    this.totalPrice = 0;
  }

  public int getId() {
    return this.id;
  }

  public List<OrderItem> getOrderItems() {
    return this.orderItems;
  }

  /**
   * Adds a food item with the given quantity to the bill.
   *
   * @param it the food item to add
   * @param quant the quantity of the food item
   */
  public void add(FoodItem it, int quant) {
    orderItems.add(new OrderItem(it, quant));
  }

  public void setTotal(double total) {
    this.totalPrice = total;
  }

  public double getTotal() {
    return this.totalPrice;
  }
}
