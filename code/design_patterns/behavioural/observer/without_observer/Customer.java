package design_patterns.behavioural.observer.without_observer;

/**
 * A customer.
 */
public class Customer {

    /**
     * This Customer's name.
     */
    private final String name;

    /**
     * Construct a new Customer with a given name.
     *
     * @param name The new Customer's name.
     */
    public Customer(String name) {
        this.name = name;
    }

    /**
     * Receives update notification from parcel objects.
     * @param sourceObject the parcel that is the object whose property has changed.
     * @param propertyName the name of the property that changed
     * @param oldValue     old value of the property
     * @param newValue     new value of the property
     */
    public void update(Parcel sourceObject, String propertyName, String oldValue, String newValue) {
        System.out.println("Customer " + this.name + " observed a change in " +
                propertyName + " of " + sourceObject);

        System.out.println(
                oldValue + " has changed to " + newValue + ". ");

        System.out.println();
    }


    @Override
    public String toString() {
        return name;
    }

}
