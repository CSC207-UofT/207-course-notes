package design_patterns.behavioural.strategy.display_strategies;

import java.util.List;

public interface Displayer<T> {

    /**
     * Displays a list of items.
     */
    void display(List<T> list);
}
