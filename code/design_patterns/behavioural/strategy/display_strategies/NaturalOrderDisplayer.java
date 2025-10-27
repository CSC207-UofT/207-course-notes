package design_patterns.behavioural.strategy.display_strategies;

import java.util.List;

public class NaturalOrderDisplayer<T> implements Displayer<T> {

    /**
     * Displays items of the list in natural order.
     */
    @Override
    public void display(List<T> list) {

        for (T t : list) {
            System.out.print("(" + t + ")  ");
        }

        System.out.println();

    }


}
