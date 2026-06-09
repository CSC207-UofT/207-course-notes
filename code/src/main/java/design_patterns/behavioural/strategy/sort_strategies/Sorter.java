package design_patterns.behavioural.strategy.sort_strategies;

import java.util.List;

public interface Sorter<T> {

    /**
     * Sorts the items in list in non-decreasing order.
     */
    void sort(List<T> list);

}