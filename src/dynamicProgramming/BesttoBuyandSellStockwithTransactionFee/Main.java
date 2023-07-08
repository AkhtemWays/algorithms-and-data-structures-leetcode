package dynamicProgramming.BesttoBuyandSellStockwithTransactionFee;

public class Main {
    private static void test1() {
        int[] stocks = {1,3,2,8,4,9};
        System.out.println(maxProfit(stocks, 2));
    }
    private static void test2() {
        int[] stocks = {1,3,7,5,10,3};
        System.out.println(maxProfit(stocks, 3));
    }
    public static void main(String[] args) {
        test1();
        test2();
    }

    public static int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[n-1][0] = prices[n-1]; // 0 состояние продажи
        dp[n-1][1] = 0; // 1 состояние покупки
        for (int i = n-2; i >= 0; i--) {
            dp[i][0] = Math.max(prices[i] + dp[i+1][1], dp[i+1][0]);
            dp[i][1] = Math.max(dp[i+1][0] - prices[i] - fee, dp[i+1][1]);
        }
        return dp[0][1];
    }
}
