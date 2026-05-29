package design_patterns.structural.facade.with_facade;

import design_patterns.structural.facade.*;

public class BillFacade {

    private final Bill bill;
    private final BillCalculator calculator;
    private final BillLogger logger;
    private final BillPrinter printer;

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