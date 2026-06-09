package design_patterns.behavioural.observer.without_observer;

public class Main {

    /**
     * The code without observer pattern.
     *
     * @author Mahsa Sadi
     * @since 2020 - 08 - 21
     */

    public static void main(String[] args) {
        Company store = new Company("Clothing-R-Us");
        Customer jen = new Customer("Jen C");
        Parcel order1 = new Parcel("TX342", "Vancouver, BC");

        order1.setCompany(store);
        order1.setCustomer(jen);
        order1.updateLocation("Calgary, AB");
        order1.updateLocation("Winnipeg, MB");
        order1.updateLocation("Toronto, ON");


    }
}