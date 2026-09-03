package designpatterns.creational.builder.withbuilder;

/** Builds {@link Pizza} instances step by step using a fluent interface. */
public class PizzaBuilder {
  private String size;
  private boolean cheese = false;
  private boolean pepperoni = false;

  /**
   * Sets the pizza size.
   *
   * @param size the pizza size
   * @return this builder
   */
  public PizzaBuilder setSize(String size) {
    this.size = size;
    return this;
  }

  /**
   * Adds cheese to the pizza.
   *
   * @return this builder
   */
  public PizzaBuilder addCheese() {
    this.cheese = true;
    return this;
  }

  /**
   * Adds pepperoni to the pizza.
   *
   * @return this builder
   */
  public PizzaBuilder addPepperoni() {
    this.pepperoni = true;
    return this;
  }

  /**
   * Builds the configured pizza.
   *
   * @return a new pizza with the configured properties
   */
  public Pizza build() {
    return new Pizza(size, cheese, pepperoni);
  }
}
