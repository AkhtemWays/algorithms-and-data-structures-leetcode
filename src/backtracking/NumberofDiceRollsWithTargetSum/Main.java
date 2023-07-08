package backtracking.NumberofDiceRollsWithTargetSum;

import java.util.HashMap;

public class Main {
    final int MOD = 1000000007;
    private HashMap<String, Integer> cache = new HashMap<>();
    public static void main(String[] args) {
        Main main = new Main();
//        System.out.println(main.numRollsToTarget(1, 6, 3)); // 1
//        System.out.println(main.numRollsToTarget(6, 6, 8)); // 6
        System.out.println(main.numRollsToTarget(30, 30, 500)); // 222616187
    }

    public int numRollsToTarget(int n, int k, int target) {
        return dfs(0, 0, k, target, n, "");
    }

    private int dfs(int total, int curN, int k, int target, int n, String dices) {
        String str = total + "_" + curN;
        if (curN == n) {
            return target == total ? 1 : 0;
        }
        if (cache.containsKey(str)) return cache.get(str);

        int count = 0;
        for (int dice = 1; dice <= Math.min(k, target - total); dice++) {
            count = count + (dfs(total + dice, curN + 1, k, target, n, dices + "_" + dice) % MOD);
        }
        cache.put(str, count);
        return count;
    }
}
