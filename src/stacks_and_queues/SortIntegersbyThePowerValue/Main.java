package stacks_and_queues.SortIntegersbyThePowerValue;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        System.out.println(getKth(12, 15, 2));
        System.out.println(getKth(7, 11, 4));
        System.out.println(getKth(10, 20, 5));
    }

    public static int getKth(int lo, int hi, int k) {
        TreeSet<Pair<Integer, Integer>> tree = new TreeSet<>((a, b) -> a.getValue() - b.getValue() == 0 ? a.getKey() - b.getKey() : a.getValue() - b.getValue());
        HashMap<Integer, Integer> memo = new HashMap<>();
        for (int val = lo; val <= hi; val++) {
            int power = getPower(val, memo);
            tree.add(new Pair<>(val, power));
        }
        for (Pair<Integer, Integer> pair : tree) {
            if (k-- <= 1) {
                return pair.getKey();
            }
        }
        return 0;
    }

    private static int getPower(int val, HashMap<Integer, Integer> memo) {
        if (memo.containsKey(val)) return memo.get(val);
        int power = 0;
        while (val != 1) {
            if (memo.containsKey(val)) 
            if (val % 2 == 0) {
                val /= 2;
            } else {
                val = 3 * val + 1;
            }
            power++;
        }
        memo.put(val, power);
        return power;
    }
}
