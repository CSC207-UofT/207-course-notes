package design_patterns.behavioural.observer.without_observer;


/**
 * A company.
 */
public class Company {

    /**
     * This Company's name.
     */
    private final String name;

    /**
     * Construct a new Company with a given name.
     *
     * @param name This Company's name.
     */
    public Company(String name) {
        this.name = name;
    }


    /**
     * Receive update notification from parcel objects.
     * @param sourceObject the parcel that is the object whose property has changed.
     * @param propertyName the name of the property that changed
     * @param oldValue     old value of the property
     * @param newValue     new value of the property
     */
    public void update(Parcel sourceObject, String propertyName, String oldValue, String newValue) {
        System.out.println("Company " + this.name + " observed a change in " +
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