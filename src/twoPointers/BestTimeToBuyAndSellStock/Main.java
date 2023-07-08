package twoPointers.BestTimeToBuyAndSellStock;

public class Main {
    public static void main(String[] args) {

    }

    public static int maxProfit(int[] prices) {
        int slow = 0;
        int maxProfit = 0;
        for (int fast = 1; fast < prices.length; fast++) {
            if (prices[fast] < prices[slow]) {
                slow = fast;
                continue;
            }
            int profit = prices[fast] - prices[slow];
            if (profit > maxProfit) maxProfit = prices[fast] - prices[slow];
            else if (profit < 0) slow++;
        }
        return maxProfit;
    }
}
