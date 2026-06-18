package designpatterns.structural.facade;

/** Calculates the total amount for a bill by summing its order items. */
public class BillCalculator {
  double tempTotal = 0;

  /**
   * Calculates the total price of the given bill.
   *
   * @param bill the bill whose order items are summed
   * @return the total amount of the bill
   */
  public double calculateTotal(Bill bill) {
    for (OrderItem f : bill.getOrderItems()) {
      tempTotal += f.getItem().getPrice() * f.getQuantity();
    }

    System.out.println("Message from Calculator: The total amount has been calculated.");

    return tempTotal;
  }
}
