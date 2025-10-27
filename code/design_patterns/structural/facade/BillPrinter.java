package design_patterns.structural.facade;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class BillPrinter {

    /*
     * This class is responsible for writing the bill into a file that is
     * to the input of the printer.
     */

    File file;

    public BillPrinter() {
        file = new File("printer-input.txt");
    }

    public void print(Bill bill) {


        try {
            PrintStream o = new PrintStream(file);

            PrintStream console = System.out;

            System.setOut(o);

            System.out.println("BillID:" + bill.getID());

            for (OrderItem it : bill.getOrderItems())
                System.out.println("Item: " + it.getItem().getName() + "   " + it.getItem().getPrice() + "$   " + "Quantity:" + it.getQuantity());

            System.out.println("Total Price: " + (bill.getTotal() + "$\n"));

            System.setOut(console);

            System.out.println("Message from Printer: The bill has been printed to " + file.getName() + " in the current directory "
                    + "to be printed by the printer.");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
