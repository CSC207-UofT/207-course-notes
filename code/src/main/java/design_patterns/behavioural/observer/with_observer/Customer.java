package design_patterns.behavioural.observer.with_observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A customer.
 */
public class Customer implements PropertyChangeListener {

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

    /*
     * PropertyChange method must be implemented in this and every observer.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        System.out.println("Customer " + this.name + " observed a change in " +
                evt.getPropertyName() + " of " + evt.getSource());

        System.out.println(
                evt.getOldValue() + " has changed to " + evt.getNewValue() + ". ");

        System.out.println();
    }


    @Override
    public String toString() {
        return name;
    }

}
