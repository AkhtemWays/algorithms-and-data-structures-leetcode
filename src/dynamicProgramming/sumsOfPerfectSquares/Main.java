package dynamicProgramming.sumsOfPerfectSquares;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        System.out.println(nSquaresFor2(24)); // 4
        System.out.println(nSquaresFor2(16)); // 1
        System.out.println(nSquaresFor2(17)); // 2
        System.out.println(nSquaresFor2(18)); // 2
        System.out.println(nSquaresFor2(19)); // 3
    }

    public static int nSquaresFor(int n) {
        if (n <= 0) return n;
        int[] dp = new int[10000];
        for (int i = 2; i < dp.length; i++) dp[i] = 1000000;
        dp[1] = 1;
        int lastBiggestFactor = 1;
        for (int sum = 2; sum <= n; sum++) {
            long factorSquared = (long) (lastBiggestFactor+1)*(lastBiggestFactor+1);
            if (sum == factorSquared) {
                dp[sum] = 1;
                lastBiggestFactor++;
                continue;
            }
            for (int factor = lastBiggestFactor; factor >= 1; factor--) {
                factorSquared = (long) factor*factor;
                if (factorSquared < sum) {
                    dp[sum] = Math.min(dp[sum - (int) factorSquared] + 1, dp[sum]);
                }
            }
        }
        return dp[n];
    }

    public static int nSquaresFor2(int n) {
        List<Integer> counts = new ArrayList<>();
        int end = (int) Math.floor(Math.sqrt(n));
        if (end*end == n){
            return 1;
        }
        for (int start = 1; start < end; start++) {
            int sum = start*start;
            int s = n - sum;
            int count = 1;
            while (sum != n) {
                int p = (int) Math.floor(Math.sqrt(s));
                sum += p * p;
                s = n - sum;
                count++;
            }
            counts.add(count);
        }
        return counts.stream().min(Integer::compareTo).get();
    }
}
