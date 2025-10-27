package design_patterns.structural.facade.without_facade;

import design_patterns.structural.facade.*;

public class Main {

    public static void main(String[] args) {

        BillCalculator calculator = new BillCalculator();
        BillLogger logger = new BillLogger();
        BillPrinter printer = new BillPrinter();

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
