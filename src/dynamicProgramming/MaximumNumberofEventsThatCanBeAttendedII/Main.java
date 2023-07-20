package dynamicProgramming.MaximumNumberofEventsThatCanBeAttendedII;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Main {
    private static void test1() {
        int[][] events = {{1,2,4},{3,4,3},{2,3,1}};
        System.out.println(maxValue(events, 3));
    }
    private static void test2() {
        int[][] events = {{1,2,4},{3,4,3},{2,3,10}};
        System.out.println(maxValue(events, 3));
    }
    private static void test3() {
        int[][] events = {{1,1,1},{2,2,2},{3,3,3},{4,4,4}};
        System.out.println(maxValue(events, 3));
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static int maxValue(int[][] events, int k) {
        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));
        int[][] memo = new int[events.length][k];
        for (int i = 0; i < events.length; i++) Arrays.fill(memo[i], -1);
        return dfs(0, -1, -1, events, memo, 0, k);
    }

    private static int dfs(int i, int lastEventEnds, int j, int[][] events, int[][] memo, int curK, int k) {
        if (i == events.length || curK == k) return 0;

        if (j != -1 && memo[j][curK] != -1) return memo[j][curK];

        int ifEventWasTaken = Integer.MIN_VALUE;
        if (lastEventEnds < events[i][0]) {
            ifEventWasTaken = dfs(i+1, events[i][1], i, events, memo, curK + 1, k) + events[i][2];
        }
        int ifEventWasSkipped = dfs(i+1, lastEventEnds, j, events, memo, curK, k);
        int answer = Math.max(ifEventWasSkipped, ifEventWasTaken);
        if (j != -1) {
            memo[j][curK] = answer;
        }
        return answer;
    }
}
