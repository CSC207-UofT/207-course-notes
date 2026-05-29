package design_patterns.creational.builder.with_builder;

public class Pizza {
    private final String size;
    private final boolean cheese;
    private final boolean pepperoni;

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
        // this uses a builder similar to what IntelliJ will generate for you if you use
        // the "replace constructor with builder" intention action refactoring.
        // This refactoring is described at:
        // https://www.jetbrains.com/help/idea/replace-constructor-with-builder.html
        Pizza pizza = new PizzaBuilder()
                .setSize("Large")
                .addCheese()
                .addPepperoni()
                .build();
        System.out.println(pizza);

    }
}
