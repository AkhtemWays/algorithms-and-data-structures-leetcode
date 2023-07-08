package design.DesignBrowserHistory;

import java.util.HashMap;

public class BrowserHistory {
    private final HashMap<Integer, String> history;
    private int current;
    private int end;
    public BrowserHistory(String homepage) {
        history = new HashMap<>();
        current = 0;
        end = 0;
        history.put(current, homepage);
    }

    public void visit(String url) {
        history.put(++current, url);
        end = current;
    }

    public String back(int steps) {
        current = Math.max(0, current - steps);
        return history.get(current);
    }

    public String forward(int steps) {
        current = Math.min(end, current + steps);
        return history.get(current);
    }
}
