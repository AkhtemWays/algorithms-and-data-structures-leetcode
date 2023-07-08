package trees.NumberofOrdersintheBacklog;

import java.util.PriorityQueue;

public class Main {
    private static void test1() {
        int[][] orders = {{10,5,0},{15,2,1},{25,1,1},{30,4,0}};
        System.out.println(getNumberOfBacklogOrders(orders));
    }
    private static void test2() {
        int[][] orders = {{7,1000000000,1},{15,3,0},{5,999999995,0},{5,1,1}};
        System.out.println(getNumberOfBacklogOrders(orders));
    }
    private static void test3() {
        int[][] orders = {{1,29,1},{22,7,1},{24,1,0},{25,15,1},{18,8,1},{8,22,0},{25,15,1},{30,1,1},{27,30,0}};
        System.out.println(getNumberOfBacklogOrders(orders));
    }
    public static void main(String[] args) {
        test1(); // 6
        test2(); // 999999984
        test3(); // 22
    }

    public static int getNumberOfBacklogOrders(int[][] orders) {
        PriorityQueue<int[]> sell = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> buy = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        final int MOD = 100_000_000_7;
        int answer = 0;
        for (int[] order : orders) {
            int price = order[0];
            int amount = order[1];
            int orderType = order[2];
            int[] newOrder = new int[]{price, amount};
            if (orderType == 0) buy.add(newOrder);
            else sell.add(newOrder);
            while (!sell.isEmpty() && !buy.isEmpty() && sell.peek()[0] <= buy.peek()[0]) {
                int k = Math.min(buy.peek()[1], sell.peek()[1]);
                buy.peek()[1] -= k;
                sell.peek()[1] -= k;
                if (buy.peek()[1] == 0) buy.poll();
                if (sell.peek()[1] == 0) sell.poll();
            }
        }

        for (int[] sellOrder : sell) {
            answer = (answer + sellOrder[1]) % MOD;
        }
        for (int[] buyOrder : buy) {
            answer = (answer + buyOrder[1]) % MOD;
        }

        return answer;
    }
}
