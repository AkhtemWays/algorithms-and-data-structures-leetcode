package arrays.BulbSwitcherII;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println(flipLights(4, 1000)); // 8
        System.out.println(flipLights(2, 2)); // 4
        System.out.println(flipLights(1, 1)); // 2
        System.out.println(flipLights(2, 1)); // 3
        System.out.println(flipLights(3, 1)); // 4
    }

    public static int flipLights(int n, int presses) {
        List<Boolean> l = new ArrayList<>();
        for (int i = 0; i < n; i++) l.add(true);

        Set<List<Boolean>> s = new HashSet<>();
        dfs(l, s, new HashSet<>(), 0, presses);
        return s.size();
    }

    private static void dfs(List<Boolean> l, Set<List<Boolean>> s, Set<List<Boolean>> cache, int presses, int pressesTotal) {
        if (presses == pressesTotal) {
            s.add(l);
            return;
        }

        if ((pressesTotal - presses) % 2 == 0 && s.contains(l)) {
            List<Boolean> button4Result = new ArrayList<>(l);
            for (int i = 0; (3 * i + 1) < l.size() ; i++) {
                button4Result.set(i, !button4Result.get(i));
            }
            dfs(button4Result, s, cache, presses+1, pressesTotal);
            return;
        }
        List<Boolean> button1Result = new ArrayList<>(l);
        List<Boolean> button2Result = new ArrayList<>(l);
        List<Boolean> button3Result = new ArrayList<>(l);
        List<Boolean> button4Result = new ArrayList<>(l);

        for (int i = 0; i < l.size(); i++) {
            button1Result.set(i, !button1Result.get(i));
        }
        for (int i = 0; i < l.size(); i+=2) {
            button2Result.set(i, !button2Result.get(i));
        }
        for (int i = 1; i < l.size(); i+=2) {
            button3Result.set(i, !button3Result.get(i));
        }
        for (int i = 0; (3 * i + 1) < l.size() ; i++) {
            button4Result.set(i, !button4Result.get(i));
        }
        dfs(button1Result, s, cache, presses+1, pressesTotal);
        dfs(button2Result, s, cache, presses+1, pressesTotal);
        dfs(button3Result, s, cache, presses+1, pressesTotal);
        dfs(button4Result, s, cache, presses+1, pressesTotal);
    }
}
