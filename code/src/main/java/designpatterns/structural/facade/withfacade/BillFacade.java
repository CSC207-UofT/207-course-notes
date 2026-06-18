package designpatterns.structural.facade.withfacade;

import designpatterns.structural.facade.Bill;
import designpatterns.structural.facade.BillCalculator;
import designpatterns.structural.facade.BillLogger;
import designpatterns.structural.facade.BillPrinter;
import designpatterns.structural.facade.FoodItem;

/** Facade that hides the subsystems involved in issuing a bill. */
public class BillFacade {

  private final Bill bill;
  private final BillCalculator calculator;
  private final BillLogger logger;
  private final BillPrinter printer;

  /** Creates the facade and sets up a sample bill. */
  public BillFacade() {
    this.calculator = new BillCalculator();
    this.logger = new BillLogger();
    this.printer = new BillPrinter();

    // Set up a sample bill during construction
    bill = new Bill(1);
    bill.add(new FoodItem("Salad", 6), 2);
    bill.add(new FoodItem("Tuna Sandwich", 7), 2);
    bill.add(new FoodItem("Fanta", 3), 2);

    System.out.println("Sample bill created in constructor.");
  }

  /** Issues the bill by calculating the total, logging it and printing it. */
  // method that interacts with the subsystems to perform the task of issuing the bill
  public void issueBill() {
    calculateTotal();
    logBill();
    printBill();
  }

  private void calculateTotal() {
    double totalPrice = calculator.calculateTotal(bill);
    bill.setTotal(totalPrice);
  }

  private void logBill() {
    logger.log(bill);
  }

  private void printBill() {
    printer.print(bill);
  }
}
