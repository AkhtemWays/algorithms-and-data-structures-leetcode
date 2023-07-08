package dynamicProgramming.BinaryTreesWithFactors;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[] arr2 = {2, 4}; // 3
        int[] arr = {2, 4, 5, 10}; // 7
        int[] arr3 = {18, 3, 6, 2};
//        System.out.println(main.numFactoredBinaryTrees(arr)); // 7
//        System.out.println(main.numFactoredBinaryTrees(arr2)); // 3
        System.out.println(main.numFactoredBinaryTrees(arr3)); // 12
    }

    public int numFactoredBinaryTrees(int[] A) {
        int MOD = 1_000_000_007;
        int N = A.length;
        Arrays.sort(A);
        long[] dp = new long[N];
        Arrays.fill(dp, 1);

        Map<Integer, Integer> index = new HashMap();
        for (int i = 0; i < N; ++i)
            index.put(A[i], i);

        for (int i = 0; i < N; ++i)
            for (int j = 0; j < i; ++j) {
                if (A[i] % A[j] == 0) { // A[j] is left child
                    int right = A[i] / A[j];
                    if (index.containsKey(right)) {
                        dp[i] = (dp[i] + dp[j] * dp[index.get(right)]) % MOD;
                    }
                }
            }

        long ans = 0;
        for (long x: dp) ans += x;
        return (int) (ans % MOD);
    }
}
