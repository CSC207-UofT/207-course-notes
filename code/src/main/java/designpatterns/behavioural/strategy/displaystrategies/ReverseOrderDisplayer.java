package designpatterns.behavioural.strategy.displaystrategies;

import java.util.List;

/**
 * A display strategy that prints the items of a list in reverse order.
 *
 * @param <T> the type of elements to display
 */
public class ReverseOrderDisplayer<T> implements Displayer<T> {

  /** Displays items of the list in reverse order. */
  @Override
  public void display(List<T> list) {

    for (int i = list.size() - 1; i >= 0; i--) {
      System.out.print("(" + list.get(i) + ")  ");
    }

    System.out.println();
  }
}
