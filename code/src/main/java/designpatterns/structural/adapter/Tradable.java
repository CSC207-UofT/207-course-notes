package designpatterns.structural.adapter;

import java.util.List;

/** Defines the operations required to trade an item between owners. */
public interface Tradable {

  /**
   * Trades the item to a new owner.
   *
   * @param newOwner the new owner of the item
   */
  void trade(String newOwner);

  /** Reverts the most recent trade. */
  void tradeUndo();

  /**
   * Returns the history of trades.
   *
   * @return the list of recorded trade events
   */
  List<String> getTradeHistory();
}
