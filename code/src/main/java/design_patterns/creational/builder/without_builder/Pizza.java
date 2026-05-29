package design_patterns.creational.builder.without_builder;

public class Pizza {
    private final String size;
    private final boolean cheese;
    private final boolean pepperoni;

    // Constructor with only size
    public Pizza(String size) {
        this(size, false);
    }

    // Constructor with size and cheese
    public Pizza(String size, boolean cheese) {
        this(size, cheese, false);
    }

    // Constructor with all fields
    public Pizza(String size, boolean cheese, boolean pepperoni) {
        this.size = size;
        this.cheese = cheese;
        this.pepperoni = pepperoni;
    }

    @Override
    public String toString() {
        return "size: " + this.size + " | cheese: " + this.cheese + " | pepperoni: " + this.pepperoni;
    }

    public static void main(String[] args) {
        Pizza pizza = new Pizza("Large",true,true);
        System.out.println(pizza);
    }
}
