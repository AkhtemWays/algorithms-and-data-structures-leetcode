package stacks_and_queues.FindTheMostCompetitiveSubsequence;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    private static void test1() {
        int[] nums = {71,18,52,29,55,73,24,42,66,8,80,2};
        System.out.println(Arrays.toString(mostCompetitive(nums, 3)));
    }
    private static void test2() {
        int[] nums = {2,4,3,3,5,4,9,6};
        System.out.println(Arrays.toString(mostCompetitive(nums, 4)));
    }
    public static void main(String[] args) {
        test1();
        test2();
    }

    public static int[] mostCompetitive(int[] nums, int k) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0]);
        int n = nums.length;
        for (int i = 0; i < n-k+1; i++) q.add(new int[]{nums[i], i});
        int[] res = new int[k];
        int i = -1;
        while (!q.isEmpty() && k > 0) {
            int[] entry = q.poll();
            int idx = entry[1];
            int value = entry[0];
            if (idx > i) {
                res[res.length-k] = value;
                k--;
                i = idx;
                if (k > 0) {
                    q.add(new int[]{nums[n-k], n-k});
                }
            }
        }

        return res;
    }
}
