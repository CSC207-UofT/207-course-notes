package designpatterns.structural.facade;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Writes a bill to a file, simulating the input to a physical printer.
 *
 * <p>This is one of the subsystems hidden behind the facade in the "with facade" example.
 */
public class BillPrinter {

  File file;

  /** Creates a printer that writes to {@code printer-input.txt} in the current directory. */
  public BillPrinter() {
    file = new File("printer-input.txt");
  }

  /**
   * Writes the given bill to the printer's file.
   *
   * @param bill the bill to print
   */
  public void print(Bill bill) {

    try {
      PrintStream o = new PrintStream(file);

      final PrintStream console = System.out;

      System.setOut(o);

      System.out.println("BillID:" + bill.getId());

      for (OrderItem it : bill.getOrderItems()) {
        System.out.println(
            "Item: "
                + it.getItem().getName()
                + "   "
                + it.getItem().getPrice()
                + "$   "
                + "Quantity:"
                + it.getQuantity());
      }

      System.out.println("Total Price: " + (bill.getTotal() + "$\n"));

      System.setOut(console);

      System.out.println(
          "Message from Printer: The bill has been printed to "
              + file.getName()
              + " in the current directory "
              + "to be printed by the printer.");

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
