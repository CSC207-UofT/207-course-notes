package design_patterns.behavioural.strategy.display_strategies;

import java.util.List;

public class ReverseOrderDisplayer<T> implements Displayer<T> {

    /**
     * Displays items of the list in reverse order.
     */
    @Override
    public void display(List<T> list) {

        for (int i = list.size() - 1; i >= 0; i--)
            System.out.print("(" + list.get(i) + ")  ");

        System.out.println();

    }

}
