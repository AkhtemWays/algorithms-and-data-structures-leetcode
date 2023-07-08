package dynamicProgramming.PutMarblesinBags;

import java.util.HashMap;

public class Main {
    private static void test1() {
        int[] weights = {1,3,5,1};
        System.out.println(putMarbles(weights, 2));
    }
    private static void test2() {
        int[] weights = {1,3};
        System.out.println(putMarbles(weights, 2));
    }
    private static void test3() {
        int[] weights = {1,4,2,5,2};
        System.out.println(putMarbles(weights, 3));
    }
    public static void main(String[] args) {
        test1(); // 4
        test2(); // 0
        test3(); // 3
    }

    private static int n;
    public static long putMarbles(int[] weights, int k) {
        n = weights.length;
        return solveMaximum(weights, k) - solveMinimum(weights, k);
    }

    private static long solveMinimum(int[] weights, int k) {
        return dfsMinimum(0, weights[0], k, new HashMap<>(), weights);
    }

    private static long dfsMinimum(int i, int lastWeight, int bagsLeft, HashMap<String, Long> memo, int[] weights) {
        if (i == n) return 0L;

        String key = i + "_" + bagsLeft + "_" + lastWeight;
        if (memo.containsKey(key)) return memo.get(key);

        long answer;
        int cost = lastWeight + weights[i];
        if (bagsLeft == n - i) {
            answer = dfsMinimum(i+1, i+1 < n ? weights[i+1] : 0, bagsLeft-1, memo, weights) + cost;
        } else if (bagsLeft == 1) {
            answer = lastWeight + weights[n-1];
        } else {
            long res1 = dfsMinimum(i+1, i+1 < n ? weights[i+1] : 0, bagsLeft-1, memo, weights) + cost;
            long res2 = dfsMinimum(i+1, lastWeight, bagsLeft, memo, weights);
            answer = Math.min(res1, res2);
        }

        memo.put(key, answer);
        return answer;
    }

    private static long solveMaximum(int[] weights, int k) {
        return dfsMaximum(0, weights[0], k, new HashMap<>(), weights);
    }

    private static long dfsMaximum(int i, int lastWeight, int bagsLeft, HashMap<String, Long> memo, int[] weights) {
        if (i == n) return 0L;

        String key = i + "_" + bagsLeft + "_" + lastWeight;
        if (memo.containsKey(key)) return memo.get(key);

        long answer;
        int cost = lastWeight + weights[i];
        if (bagsLeft == n - i) {
            answer = dfsMaximum(i+1, i+1 < n ? weights[i+1] : 0, bagsLeft-1, memo, weights) + cost;
        } else if (bagsLeft == 1) {
            answer = lastWeight + weights[n-1];
        } else {
            long res1 = dfsMaximum(i+1, i+1 < n ? weights[i+1] : 0, bagsLeft-1, memo, weights) + cost;
            long res2 = dfsMaximum(i+1, lastWeight, bagsLeft, memo, weights);
            answer = Math.max(res1, res2);
        }

        memo.put(key, answer);
        return answer;
    }
}
