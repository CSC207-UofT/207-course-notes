package designpatterns.structural.adapter;

import java.time.LocalDateTime;

/** A simple legacy ticket with a basic set of functionalities. */
public class Ticket {

  /*
   * A simple legacy ticket class with a basic set of functionalities.
   */

  protected String id;
  protected String owner;
  protected String showName;
  protected LocalDateTime date;
  protected String seat;

  /**
   * Creates a ticket with the given details.
   *
   * @param id the ticket identifier
   * @param show the show name
   * @param date the date of the show
   * @param seat the seat label
   */
  public Ticket(String id, String show, LocalDateTime date, String seat) {
    this.id = id;
    this.showName = show;
    this.date = date;
    this.seat = seat;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getOwner() {
    return this.owner;
  }

  /**
   * Returns a string representation of this ticket.
   *
   * @return a description of the ticket owner and show
   */
  public String toString() {
    return "Ticket owned by " + owner + " for " + showName;
  }
}
