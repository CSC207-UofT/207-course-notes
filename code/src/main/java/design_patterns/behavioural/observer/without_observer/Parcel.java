package design_patterns.behavioural.observer.without_observer;

/**
 * An observable parcel with a tracking number and location.
 */
public class Parcel {

    /**
     * This Parcel's tracking number.
     */
    private final String trackingNumber;

    /**
     * This Parcel's location.
     */
    private String location;

    /**
     * This parcel's customer.
     */
    private Customer customer;

    /**
     * This parcel's company;
     */
    private Company company;

    /**
     * Constructs a new Parcel with the given tracking number and
     * location.
     *
     * @param trackingNumber This Parcel's tracking number.
     * @param location       This Parcel's location.
     */
    public Parcel(String trackingNumber, String location) {
        this.trackingNumber = trackingNumber;
        this.location = location;

    }

    public void setCustomer(Customer cust) {
        this.customer = cust;
    }

    public void setCompany(Company comp) {
        this.company = comp;
    }

    @Override
    public String toString() {
        return "Parcel " + trackingNumber + ".";
    }


    /**
     * Sets this Parcel's location to newLocation and calls the locationChanged method.
     *
     * @param newLocation This Parcel's new location.
     */
    public void updateLocation(String newLocation) {

        String oldLocation = this.location;
        this.location = newLocation;
        locationChanged("location", oldLocation, newLocation);

    }

    /**
     * This method is responsible for updating all the classes for which the location
     * of the parcel is important.
     */
    public void locationChanged(String propertyName, String oldValue, String newValue) {

        this.company.update(this, propertyName, oldValue, newValue);
        this.customer.update(this, propertyName, oldValue, newValue);
    }
}
