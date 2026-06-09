package design_patterns.structural.facade;

public class OrderItem {

    private final FoodItem item;
    private final int quantity;

    public OrderItem(FoodItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public FoodItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public String toString() {
        return item.toString() + "," + quantity;
    }

}
