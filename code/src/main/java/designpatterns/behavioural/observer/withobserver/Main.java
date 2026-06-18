package designpatterns.behavioural.observer.withobserver;

/** Demonstrates parcel tracking implemented with the observer pattern. */
public class Main {

  /*
   * Design Pattern: Observer
   * Note: Observer pattern in Java 9 and later version using java.beans.
   * @author: Mahsa Sadi
   * @since 2020 - 08 - 21
   *
   */

  /**
   * Runs the observer pattern demonstration.
   *
   * @param args command-line arguments (unused)
   */
  public static void main(String[] args) {
    Company store = new Company("Clothing-R-Us");
    Customer jen = new Customer("Jen C");
    Parcel order1 = new Parcel("TX342", "Vancouver, BC");

    order1.addObserver(store);
    order1.addObserver(jen);
    order1.updateLocation("Calgary, AB");
    order1.updateLocation("Winnipeg, MB");
    order1.updateLocation("Toronto, ON");
  }
}
