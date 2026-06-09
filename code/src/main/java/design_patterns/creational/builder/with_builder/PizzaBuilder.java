package design_patterns.creational.builder.with_builder;

public class PizzaBuilder {
    private String size;
    private boolean cheese = false;
    private boolean pepperoni = false;

    public PizzaBuilder setSize(String size) {
        this.size = size;
        return this;
    }

    public PizzaBuilder addCheese() {
        this.cheese = true;
        return this;
    }

    public PizzaBuilder addPepperoni() {
        this.pepperoni = true;
        return this;
    }

    public Pizza build() {
        return new Pizza(size, cheese, pepperoni);
    }
}