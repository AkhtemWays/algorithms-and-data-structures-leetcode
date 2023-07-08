package hashmap.ProfitableSchemes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int[] group = {2, 2};
        int[] profit = {2, 3};
        System.out.println(profitableSchemes(5, 3, group, profit));
        int[] group2 = {2, 3, 5};
        int[] profit2 = {6, 7, 8};
        System.out.println(profitableSchemes(10, 5, group2, profit2));
    }

    private static int MOD = 100_000_000_7;

    private static class Profit {
        int profit;
        int index;
        Profit(int profit, int index) {
            this.profit = profit;
            this.index = index;
        }
    }
    public static int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        Profit[] profits = new Profit[profit.length];
        for (int i = 0; i < profit.length; i++) profits[i] = new Profit(profit[i], i);

        Arrays.sort(profits, Comparator.comparingInt(a -> a.profit));

        return dfs(0, 0, 0, n, profits, minProfit, group, new HashMap<>()) % MOD;
    }

    private static int dfs(int i, int curProfit, int k, int n, Profit[] profits, int minProfit, int[] group, HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>> memo) {
        if (k > n) return 0;
        if (curProfit >= minProfit) return 1;
        if (i == profits.length) {
            return 0;
        }

        int cached = getFromCache(memo, k, curProfit, i);
        if (cached != -1) return cached;

        Profit profit = profits[i];
        int added = dfs(i+1, curProfit + profit.profit, k + group[profit.index], n, profits, minProfit, group, memo) % MOD;
        int skipped = dfs(i+1, curProfit, k, n, profits, minProfit, group, memo) % MOD;

        int result = (added + skipped) % MOD;
        putIntoCache(memo, k, curProfit, i, result);
        return result;
    }

    private static int getFromCache(HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>> memo, int k, int curProfit, int i) {
        if (memo.containsKey(k)) {
            HashMap<Integer, HashMap<Integer, Integer>> firstLevel = memo.get(k);
            if (firstLevel.containsKey(curProfit)) {
                HashMap<Integer, Integer> secondLevel = firstLevel.get(curProfit);
                if (secondLevel.containsKey(i)) {
                    return secondLevel.get(i);
                }
                return -1;
            }
            return -1;
        }
        return -1;
    }

    private static void putIntoCache(HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>> memo, int k, int curProfit, int i, int value) {
        if (memo.containsKey(k)) {
            HashMap<Integer, HashMap<Integer, Integer>> firstLevel = memo.get(k);
            if (firstLevel.containsKey(curProfit)) {
                HashMap<Integer, Integer> secondLevel = firstLevel.get(curProfit);
                secondLevel.put(i, value);
            } else {
                HashMap<Integer, Integer> secondLevel = new HashMap<>();
                secondLevel.put(i, value);
                firstLevel.put(curProfit, secondLevel);
            }
        } else {
            HashMap<Integer, HashMap<Integer, Integer>> firstLevel = new HashMap<>();
            HashMap<Integer, Integer> secondLevel = new HashMap<>();
            secondLevel.put(i, value);
            firstLevel.put(curProfit, secondLevel);
            memo.put(k, firstLevel);
        }
    }
}
