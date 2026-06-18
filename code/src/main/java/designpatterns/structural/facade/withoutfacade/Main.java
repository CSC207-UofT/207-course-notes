package designpatterns.structural.facade.withoutfacade;

import designpatterns.structural.facade.Bill;
import designpatterns.structural.facade.BillCalculator;
import designpatterns.structural.facade.BillLogger;
import designpatterns.structural.facade.BillPrinter;
import designpatterns.structural.facade.FoodItem;

/** Demonstrates issuing a bill by talking to each subsystem directly, WITHOUT a facade. */
public class Main {

  /**
   * Runs the demonstration.
   *
   * @param args command-line arguments (unused)
   */
  public static void main(String[] args) {

    final BillCalculator calculator = new BillCalculator();
    final BillLogger logger = new BillLogger();
    final BillPrinter printer = new BillPrinter();

    // Set up a sample bill
    Bill bill = new Bill(1);
    bill.add(new FoodItem("Salad", 6), 2);
    bill.add(new FoodItem("Tuna Sandwich", 7), 2);
    bill.add(new FoodItem("Fanta", 3), 2);

    System.out.println("Sample bill created in main.");

    // interact directly with the subsystems to issue the bill
    calculator.calculateTotal(bill);
    logger.log(bill);
    printer.print(bill);
  }
}
