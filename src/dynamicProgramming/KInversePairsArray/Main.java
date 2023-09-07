package dynamicProgramming.KInversePairsArray;

public class Main {
    private static void test1() {
        System.out.println(kInversePairs(3, 1));
    }
    public static void main(String[] args) {
        test1();
    }

    private static final int MOD = 100_000_000_7;
    private static final Integer[][] memo = new Integer[1001][1001];
    public static int kInversePairs(int n, int k) {
        if (memo[n-1][k] != null) return memo[n-1][k];
        for (int i = 0; i < n; i++) memo[i][0] = 1;

        return dfs(1, k, memo);
    }

    private static int dfs(int size, int k, Integer[][] memo) {
        if (size > memo.length || k < 0) return 0;

        if (memo[size-1][k] != null) return memo[size-1][k];

        int answer = 0;

        for (int i = Math.max(0, size - k); i < size; i++) {
            if (size - i <= k) {
                answer = (answer + dfs(size + 1, k - size + i, memo)) % MOD;
            }
        }

        answer = (answer + dfs(size + 1, k, memo)) % MOD;

        return memo[size-1][k] = answer;
    }
}
