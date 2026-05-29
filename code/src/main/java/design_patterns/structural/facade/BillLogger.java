package design_patterns.structural.facade;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class BillLogger {

    /*
     * This class is responsible for writing the bill to a file.
     */
    File file;


    public BillLogger() {
        createFile();
    }


    public void log(Bill bill) {
        writeInFile(bill);
    }


    private void createFile() {
        try {
            file = new File("bill-log.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void writeInFile(Bill bill) {
        try {
            FileWriter myWriter = new FileWriter(file);

            myWriter.write(bill.getID() + "\n");

            for (OrderItem it : bill.getOrderItems())
                myWriter.write(it.toString() + "\n");

            myWriter.write(bill.getTotal() + "\n");

            myWriter.close();

            System.out.println("Message from Logger: The bill has been logged in " + file.getName() + " in the current directory.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
