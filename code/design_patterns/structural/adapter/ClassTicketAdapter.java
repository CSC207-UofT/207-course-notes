package design_patterns.structural.adapter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClassTicketAdapter extends Ticket implements Tradable {
    /*
     * Adapter (with inheritance))
     * This adapter extends the adaptee (i.e, ticket) and implements the trade methods thus showing
     * the behavior of both ticket and Tradable.
     *
     */

    private final List<String> tradeHistory;

    public ClassTicketAdapter(String id, String show, LocalDateTime date, String seat) {
        super(id, show, date, seat);
        tradeHistory = new ArrayList<>();
    }

    public String toString() {
        return super.toString();
    }

    public void trade(String newOwner) {
        String previousOwner = getOwner();
        setOwner(newOwner);
        this.addToHistory("(" + previousOwner + ", " + newOwner + ")");
    }

    public void addToHistory(String event) {
        tradeHistory.add(event);
    }

    public List<String> getTradeHistory() {
        return tradeHistory;
    }

    public void tradeUndo() {
        int lastTrade = getTradeHistory().size() - 1;
        String tradeLogOfLastTrade = getTradeHistory().get(lastTrade);
        String previousOwner = tradeLogOfLastTrade.split(",")[0].replace("(", "");
        setOwner(previousOwner);
        getTradeHistory().remove(lastTrade);

    }
}