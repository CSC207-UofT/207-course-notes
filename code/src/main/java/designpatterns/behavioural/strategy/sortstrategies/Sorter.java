package designpatterns.behavioural.strategy.sortstrategies;

import java.util.List;

/**
 * A strategy for sorting a list of items.
 *
 * @param <T> the type of elements to sort
 */
public interface Sorter<T> {

  /** Sorts the items in list in non-decreasing order. */
  void sort(List<T> list);
}
