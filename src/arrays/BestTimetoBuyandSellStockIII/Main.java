package arrays.BestTimetoBuyandSellStockIII;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] nums = {3,3,5,0,0,3,1,4};
        int[] nums2 = {1,4,3,7,3,6,5,8,0,5,3,10};
        int[] nums3 = {1,4,3,7};
        int[] nums4 = {7,6,4,3,1};
        int[] nums5 = {1,2,3,4,5};
        int[] nums6 = {3,2,6,5,0,3};
        int[] nums7 = {4,7,2,1,11};
        System.out.println(maxProfit(nums)); // 6
        System.out.println(maxProfit(nums2)); // 17
        System.out.println(maxProfit(nums3)); // 7
        System.out.println(maxProfit(nums4)); // 0
        System.out.println(maxProfit(nums5)); // 4
        System.out.println(maxProfit(nums6)); // 7
        System.out.println(maxProfit(nums7)); // 13
    }

    public static int maxProfit(int[] prices) {
        int n = prices.length;
        List<Integer> mq = new ArrayList<>();
        int[] maxSoFar = new int[n];
        mq.add(prices[0]);
        for (int i = 1; i < n; i++) {
            while (!mq.isEmpty() && mq.get(mq.size()-1) > prices[i]) {
                mq.remove(mq.size()-1);
            }
            mq.add(prices[i]);
            maxSoFar[i] = Math.max(maxSoFar[i-1], Math.max(0, mq.get(mq.size()-1) - mq.get(0)));
        }

        int answer = 0;
        int maxIndex = n-1;
        for (int i = n-1; i >= 1; i--) {
            int firstTransaction = maxSoFar[i-1];
            int secondTransaction = prices[maxIndex] - prices[i];
            answer = Math.max(answer, firstTransaction + secondTransaction);
            if (prices[i] > prices[maxIndex]) {
                maxIndex = i;
            }
        }
        return Math.max(answer, Arrays.stream(maxSoFar).max().getAsInt());
    }
}
