package design_patterns.behavioural.observer.with_observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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
     * Helper class for making this class be observable
     */
    private final PropertyChangeSupport observableSupport;

    /**
     * Constructs a new Parcel with the given tracking number and location.
     *
     * @param trackingNumber This parcel's tracking number.
     * @param location       This parcel's location.
     */
    public Parcel(String trackingNumber, String location) {
        this.trackingNumber = trackingNumber;
        this.location = location;
        this.observableSupport = new PropertyChangeSupport(this);

    }

    @Override
    public String toString() {
        return "Parcel " + trackingNumber + ".";
    }

    /**
     * Add a new observer to observe the changes to this parcel.
     * @param observer the observer to associate with this parcel
     */
    public void addObserver(PropertyChangeListener observer) {
        observableSupport.addPropertyChangeListener("location", observer);
    }

    /**
     * Sets this Parcel's location to newLocation and notifies its observers.
     *
     * @param newLocation This Parcel's new location.
     */
    public void updateLocation(String newLocation) {

        String oldLocation = this.location;
        this.location = newLocation;
        observableSupport.firePropertyChange("location", oldLocation, newLocation);
    }
}
