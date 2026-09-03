package designpatterns.structural.adapter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** Adapter that makes a ticket tradable using inheritance. */
public class ClassTicketAdapter extends Ticket implements Tradable {
  /*
   * Adapter (with inheritance))
   * This adapter extends the adaptee (i.e, ticket) and implements the trade methods thus showing
   * the behavior of both ticket and Tradable.
   *
   */

  private final List<String> tradeHistory;

  /**
   * Creates a class ticket adapter with the given ticket details.
   *
   * @param id the ticket identifier
   * @param show the show name
   * @param date the date of the show
   * @param seat the seat label
   */
  public ClassTicketAdapter(String id, String show, LocalDateTime date, String seat) {
    super(id, show, date, seat);
    tradeHistory = new ArrayList<>();
  }

  /**
   * Returns a string representation of this ticket.
   *
   * @return the ticket description
   */
  public String toString() {
    return super.toString();
  }

  /**
   * Trades the ticket to a new owner and records the trade.
   *
   * @param newOwner the new owner of the ticket
   */
  public void trade(String newOwner) {
    String previousOwner = getOwner();
    setOwner(newOwner);
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
    setOwner(previousOwner);
    getTradeHistory().remove(lastTrade);
  }
}
