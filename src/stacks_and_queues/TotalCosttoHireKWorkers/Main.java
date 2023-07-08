package stacks_and_queues.TotalCosttoHireKWorkers;

import java.util.PriorityQueue;

public class Main {
    private static void test1() {
        int[] costs = {17,12,10,2,7,2,11,20,8};
        System.out.println(totalCost(costs, 3, 4));
    }
    private static void test2() {
        int[] costs = {1,2,4,1};
        System.out.println(totalCost(costs, 3, 3));
    }
    public static void main(String[] args) {
        test1();
        test2();
    }

    public static long totalCost(int[] costs, int k, int candidates) {
        int n = costs.length;
        long answer = 0;
        PriorityQueue<Integer> left = new PriorityQueue<>();
        PriorityQueue<Integer> right = new PriorityQueue<>();
        for (int i = 0; i < Math.min(candidates, n/2); i++) {
            left.add(costs[i]);
            right.add(costs[n-i-1]);
        }
        int i = candidates, j = n - candidates - 1;
        while (k > 0 && i <= j) {
            k--;
            if (left.peek() <= right.peek()) {
                answer += left.poll();
                left.add(costs[i++]);
            } else {
                answer += right.poll();
                right.add(costs[j--]);
            }
        }

        while (k-- > 0) {
            if (left.peek() <= right.peek()) {
                answer += left.poll();
            } else {
                answer += right.poll();
            }
        }

        return answer;
    }
}
