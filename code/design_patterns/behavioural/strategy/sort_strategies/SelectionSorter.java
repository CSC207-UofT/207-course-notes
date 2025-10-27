package design_patterns.behavioural.strategy.sort_strategies;

import java.util.List;

/*
 * A restriction on the type that can be used to instantiate a SelectionSort:
 * only those types T that implement the interface Comparable<T>.
 */
public class SelectionSorter<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public void sort(List<T> list) {

        for (int i = 0; i < list.size() - 1; i++) {

            // Find the index of the smallest item in the list between
            // indices i and list.size() - 1 inclusive.
            int indexSmallest = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(indexSmallest).compareTo(list.get(j)) > 0) {
                    indexSmallest = j;
                }
            }

            // Swap the item at index i with the smallest item
            // between i and  list.size() - 1 inclusive.
            T temp = list.get(i);
            list.set(i, list.get(indexSmallest));
            list.set(indexSmallest, temp);
        }
    }
}