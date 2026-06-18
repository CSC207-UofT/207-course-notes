package designpatterns.creational.builder.withoutbuilder;

/** A pizza constructed using telescoping constructors. */
public class Pizza {
  private final String size;
  private final boolean cheese;
  private final boolean pepperoni;

  // Constructor with only size
  /**
   * Creates a pizza with only a size.
   *
   * @param size the pizza size
   */
  public Pizza(String size) {
    this(size, false);
  }

  // Constructor with size and cheese
  /**
   * Creates a pizza with a size and cheese option.
   *
   * @param size the pizza size
   * @param cheese whether the pizza has cheese
   */
  public Pizza(String size, boolean cheese) {
    this(size, cheese, false);
  }

  // Constructor with all fields
  /**
   * Creates a pizza with all fields.
   *
   * @param size the pizza size
   * @param cheese whether the pizza has cheese
   * @param pepperoni whether the pizza has pepperoni
   */
  public Pizza(String size, boolean cheese, boolean pepperoni) {
    this.size = size;
    this.cheese = cheese;
    this.pepperoni = pepperoni;
  }

  @Override
  public String toString() {
    return "size: " + this.size + " | cheese: " + this.cheese + " | pepperoni: " + this.pepperoni;
  }

  /**
   * Creates a pizza using the full constructor and prints it.
   *
   * @param args command-line arguments (unused)
   */
  public static void main(String[] args) {
    Pizza pizza = new Pizza("Large", true, true);
    System.out.println(pizza);
  }
}
