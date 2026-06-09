package design_patterns.structural.facade;

public class FoodItem {

    private final String name;
    private final double price;

    public FoodItem(String it, double price) {
        this.name = it;
        this.price = price;
    }


    public String getName() {
        return name;
    }


    public double getPrice() {
        return price;
    }


    public String toString() {
        return name + "," + price;
    }


}
