package design_patterns.behavioural.strategy.sort_strategies;

import java.util.List;

/*
 * A restriction on the type that can be used to instantiate a SelectionSort:
 * only those types T that implement the interface Comparable<T>.
 */
public class InsertionSorter<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public void sort(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            T temp = list.get(i);

            // Insert item from index i into its correct sorted location
            // between 0 and i inclusive.
            int j;
            for (j = i - 1; j >= 0 && temp.compareTo(list.get(j)) < 0; j--) {
                list.set(j + 1, list.get(j));
            }
            list.set(j + 1, temp);
        }
    }
}