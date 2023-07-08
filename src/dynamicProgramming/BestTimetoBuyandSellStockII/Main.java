package dynamicProgramming.BestTimetoBuyandSellStockII;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[] prices = {7, 1, 5, 3, 6, 5}; // 7
        int[] prices1 = {1, 2, 3, 4, 5}; // 4
        int[] prices2 = {7, 6, 4, 2, 1}; // 0
        int[] prices3 = {1, 2, 3, 4, 1, 77, 99};
        System.out.println(main.maxProfit(prices));
        System.out.println(main.maxProfit(prices1));
        System.out.println(main.maxProfit(prices2));
        System.out.println(main.maxProfit(prices3));
    }

    public int maxProfit(int[] prices) {
        if (prices.length == 0 || prices.length == 1) return 0;
        int min = prices[0];
        int minAfterLastProfit = min;
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            min = Math.min(min, price);
            if (prices[i] - prices[i-1] > 0) {
                if (price - minAfterLastProfit + profit > price - min) {
                    profit += price - minAfterLastProfit;
                } else {
                    profit = price - min;
                }
            }
            minAfterLastProfit = price;
        }


        return profit;
    }

}
