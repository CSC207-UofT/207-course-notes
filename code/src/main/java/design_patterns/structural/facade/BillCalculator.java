package design_patterns.structural.facade;

public class BillCalculator {
    double tempTotal = 0;

    public double calculateTotal(Bill bill) {
        for (OrderItem f : bill.getOrderItems())
            tempTotal += f.getItem().getPrice() * f.getQuantity();


        System.out.println("Message from Calculator: The total amount has been calculated.");

        return tempTotal;
    }

}
