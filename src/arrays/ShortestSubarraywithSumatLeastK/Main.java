package arrays.ShortestSubarraywithSumatLeastK;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        int[] nums = {2,-1,2};
        int[] nums2 = {1,2};
        int[] nums3 = {1};
        int[] nums4 = {84,-37,32,40,95};
        int[] nums5 = {17,85,93,-45,-21};
        int[] nums6 = {-28,81,-20,28,-29};
        System.out.println(shortestSubarray(nums, 3)); // 3
        System.out.println(shortestSubarray(nums2, 4)); // -1
        System.out.println(shortestSubarray(nums3, 1)); // 1
        System.out.println(shortestSubarray(nums4, 167)); // 3
        System.out.println(shortestSubarray(nums5, 150)); // 2
        System.out.println(shortestSubarray(nums6, 89)); // 3
    }

    public static int shortestSubarray(int[] nums, int k) {
        Deque<int[]> deque = new LinkedList<>();
        int minLength = Integer.MAX_VALUE;
        for (int i = 0, curSum = 0; i < nums.length; i++) {
            curSum += nums[i];
            while (!deque.isEmpty() && deque.peekLast()[0] >= curSum) {
                deque.pollLast();
            }
            while (!deque.isEmpty() && curSum - deque.peekFirst()[0] >= k) {
                int[] headValue = deque.pollFirst();
                minLength = Math.min(minLength, i - headValue[1]);
            }
            deque.addLast(new int[]{curSum, i});
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }
}
