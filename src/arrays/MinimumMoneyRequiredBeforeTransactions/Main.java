package arrays.MinimumMoneyRequiredBeforeTransactions;

import java.util.Arrays;

public class Main {
    private static void test1() {
        int[][] transactions = {{2,1},{5,0},{4,2}};
        System.out.println(minimumMoney(transactions));
    }
    private static void test2() {
        int[][] transactions = {{3,0},{0,3}};
        System.out.println(minimumMoney(transactions));
    }
    private static void test3() {
        int[][] transactions = {{5,1},{5,2},{3,2}};
        System.out.println(minimumMoney(transactions));
    }
    public static void main(String[] args) {
        test1(); // 10
        test2(); // 3
        test3(); // 9
    }

    public static long minimumMoney(int[][] transactions) {
        long sum = 0;
        int cashback = 0;
        for (int[] transaction : transactions) {
            sum += Math.max(0, transaction[0] - transaction[1]);
            cashback = Math.max(cashback, Math.min(transaction[0], transaction[1]));
        }
        return sum + cashback;
    }
}
