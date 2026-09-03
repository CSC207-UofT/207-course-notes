package designpatterns.behavioural.strategy.displaystrategies;

import java.util.List;

/**
 * A display strategy that prints the items of a list in natural order.
 *
 * @param <T> the type of elements to display
 */
public class NaturalOrderDisplayer<T> implements Displayer<T> {

  /** Displays items of the list in natural order. */
  @Override
  public void display(List<T> list) {

    for (T t : list) {
      System.out.print("(" + t + ")  ");
    }

    System.out.println();
  }
}
