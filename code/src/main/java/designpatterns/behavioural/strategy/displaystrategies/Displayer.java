package designpatterns.behavioural.strategy.displaystrategies;

import java.util.List;

/**
 * A strategy for displaying a list of items.
 *
 * @param <T> the type of elements to display
 */
public interface Displayer<T> {

  /** Displays a list of items. */
  void display(List<T> list);
}
