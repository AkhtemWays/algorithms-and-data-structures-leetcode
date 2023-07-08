package stacks_and_queues.MinimumCosttoCutaStick;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Main {
    private static void test1() {
        int[] cuts = {1,3,4,5};
        System.out.println(minCost(7, cuts));
    }
    private static void test2() {
        int[] cuts = {5,6,1,4,2};
        System.out.println(minCost(9, cuts));
    }
    private static void test3() {
        int[] cuts = {1,14,18,6,17,8,10,4,13,16,7};
        System.out.println(minCost(20, cuts));
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static int minCost(int n, int[] cutz) {
        PriorityQueue<int[]> intervals = new PriorityQueue<>((a, b) -> a[1] - a[0] - (b[1] - b[0]));
        intervals.offer(new int[]{0, n});
        Set<Integer> cuts = new HashSet<>();
        for (int cut : cutz) cuts.add(cut);
        int answer = 0;

        while (!intervals.isEmpty() && !cuts.isEmpty()) {
            int optimalCut = Integer.MAX_VALUE;
            int cut = -1;
            int[] interval = intervals.poll();
            int left = interval[0];
            int right = interval[1];
            for (int c : cuts) {
                if (c >= left && c <= right) {
                    int candidateCut = Math.max(right - c, c - left);
                    if (candidateCut < optimalCut) {
                        optimalCut = candidateCut;
                        cut = c;
                    }
                }
            }
            if (cut == -1) continue;

            int[] leftInterval = new int[]{left, cut};
            int[] rightInterval = new int[]{cut, right};
            intervals.offer(leftInterval);
            intervals.offer(rightInterval);
            answer += right - left;
            cuts.remove(cut);
        }

        return answer;
    }
}
