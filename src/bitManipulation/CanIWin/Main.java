package bitManipulation.CanIWin;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        System.out.println(canIWin(10, 11));
        System.out.println(canIWin(10, 40));
    }

    public static boolean canIWin(int k, int desiredTotal) {
        int sum = ((k+1)*k)/2;
        if (sum < desiredTotal) return false;
        if (sum == desiredTotal) return k % 2 != 0;
        int bitmask = ((1 << k) - 1) << 1;
        HashMap<Integer, Boolean> cache = new HashMap<>();
        return dfs(bitmask, desiredTotal, k, cache);
    }

    private static boolean dfs(int stateBitmask, int totalLeft, int k, HashMap<Integer, Boolean> cache) {
        for (int i = k; i >= totalLeft; i--) {
            if (((stateBitmask >> i) & 1) == 1) {
                if (totalLeft - i <= 0) {
                    return true;
                }
            }
        }

        int key = stateBitmask | (totalLeft << k+1);

        if (cache.containsKey(key)) return cache.get(key);
        for (int i = 1; i <= k; i++) {
            if (((stateBitmask >> i) & 1) > 0) {
                if (!dfs(stateBitmask ^ (1 << i), totalLeft - i, k, cache)) {
                    cache.put(key, true);
                    return true;
                }
            }
        }
        cache.put(key, false);
        return false;
    }
}
