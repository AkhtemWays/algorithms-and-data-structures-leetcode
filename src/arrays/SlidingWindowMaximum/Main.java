package arrays.SlidingWindowMaximum;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int[] nums2 = {1,3,1,2,0,5};
        System.out.println(Arrays.toString(maxSlidingWindow(nums, 3)));
        System.out.println(Arrays.toString(maxSlidingWindow(nums2, 3)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return nums;
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            if (deque.isEmpty()) deque.addLast(i);
            else if (nums[i] < nums[deque.peekLast()]) {
                deque.addLast(i);
            } else {
                while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                    deque.pollLast();
                }
                deque.addLast(i);
            }
        }
        result[0] = nums[deque.peekFirst()];
        for (int i = k; i < nums.length; i++) {
            if (deque.peekFirst() < i-k + 1) {
                deque.pollFirst();
            }
            if (deque.isEmpty()) {
                deque.addLast(i);
            }
            else if (nums[i] < nums[deque.peekLast()]) {
                deque.addLast(i);
            }
            else {
                while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                    deque.pollLast();
                }
                deque.addLast(i);
            }
            result[i-k+1] = nums[deque.peekFirst()];
        }
        return result;
    }
}
