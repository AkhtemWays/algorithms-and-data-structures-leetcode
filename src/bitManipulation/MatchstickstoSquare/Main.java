package bitManipulation.MatchstickstoSquare;

import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 2};
        System.out.println(makesquare(nums));
    }

    private static int n;
    public static boolean makesquare(int[] matchsticks) {
        n = matchsticks.length;
        HashSet<Integer> cache = new HashSet<>();
        int sum = Arrays.stream(matchsticks).sum();
        if (sum % 4 != 0) return false;
        int targetSide = sum / 4;
        return dfs(0, cache, matchsticks, 0, targetSide, 0);
    }

    private static boolean dfs(int mask, HashSet<Integer> cache, int[] matchsticks, int sidesDone, int targetSide, int curSide) {
        if (curSide > targetSide) return false;
        if (curSide == targetSide) {
            sidesDone++;
            curSide = 0;
        }

        if (sidesDone == 4) return true;

        if (cache.contains(mask)) return false;

        for (int i = 0; i < n; i++) {
            if (((mask >> i) & 1) == 0) {
                if (dfs(mask | (1 << i), cache, matchsticks, sidesDone, targetSide, curSide + matchsticks[i])) return true;
            }
        }
        cache.add(mask);
        return false;
    }
}
