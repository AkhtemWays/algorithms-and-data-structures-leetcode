package stacks_and_queues.EarliestPossibleDayofFullBloom;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    private static void test1() {
        int[] plantTime = {1,2,3,2};
        int[] growTime = {2,1,2,1};
        System.out.println(earliestFullBloom(plantTime, growTime));
    }
    private static void test2() {
        int[] plantTime = {1,4,3};
        int[] growTime = {2,3,1};
        System.out.println(earliestFullBloom(plantTime, growTime));
    }
    private static void test3() {
        int[] plantTime = {1};
        int[] growTime = {1};
        System.out.println(earliestFullBloom(plantTime, growTime));
    }
    private static void test4() {
        int[] plantTime = {100,2,4};
        int[] growTime = {100,3,20};
        System.out.println(earliestFullBloom(plantTime, growTime));
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

    public static int earliestFullBloom(int[] plantTime, int[] growTime) {
        int n = plantTime.length;
        Comparator<int[]> comparator = (a, b) -> b[1] - a[1];
        PriorityQueue<int[]> q = new PriorityQueue<>(comparator);
        for (int i = 0; i < n; i++) {
             q.offer(new int[]{plantTime[i], growTime[i]});
        }

        int currentDay = 0;
        int maxDays = 0;
        while (!q.isEmpty()) {
            int[] entry = q.poll();
            currentDay += entry[0];
            maxDays = Math.max(maxDays, currentDay + entry[1]);
        }
        return maxDays;
    }
}
