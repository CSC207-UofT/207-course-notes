package designpatterns.structural.adapter;

import java.util.ArrayList;
import java.util.List;

/** Adapter that makes a ticket tradable using delegation. */
public class ObjectTicketAdapter implements Tradable {

  /*
   * Adapter (with delegation))
   *
   * An adapter class that allows a ticket to be traded (i.e; be sold to other customers).
   * This adapter uses delegation; i.e. the adapter contains an instance of the adaptee class;
   * i.e., the Ticket Class.
   */

  private final Ticket ticket;
  private final List<String> tradeHistory;

  /**
   * Creates an adapter wrapping the given ticket.
   *
   * @param ticket the ticket to adapt
   */
  public ObjectTicketAdapter(Ticket ticket) {
    this.ticket = ticket;
    this.tradeHistory = new ArrayList<>();
  }

  /**
   * Sets the owner of the wrapped ticket.
   *
   * @param owner the new owner
   */
  public void setOwner(String owner) {
    ticket.setOwner(owner);
  }

  /**
   * Trades the ticket to a new owner and records the trade.
   *
   * @param newOwner the new owner of the ticket
   */
  public void trade(String newOwner) {
    String previousOwner = this.ticket.getOwner();
    this.ticket.setOwner(newOwner);
    this.addToHistory("(" + previousOwner + ", " + newOwner + ")");
  }

  /**
   * Adds a trade event to the trade history.
   *
   * @param event the trade event to record
   */
  public void addToHistory(String event) {
    tradeHistory.add(event);
  }

  public List<String> getTradeHistory() {
    return tradeHistory;
  }

  /** Reverts the most recent trade, restoring the previous owner. */
  public void tradeUndo() {
    int lastTrade = getTradeHistory().size() - 1;
    String tradeLogOfLastTrade = getTradeHistory().get(lastTrade);
    String previousOwner = tradeLogOfLastTrade.split(",")[0].replace("(", "");
    this.ticket.setOwner(previousOwner);
    getTradeHistory().remove(lastTrade);
  }

  /**
   * Returns a string representation of the wrapped ticket.
   *
   * @return the ticket description
   */
  public String toString() {
    return ticket.toString();
  }
}
