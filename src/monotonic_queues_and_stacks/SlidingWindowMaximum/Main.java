package monotonic_queues_and_stacks.SlidingWindowMaximum;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int[] nums2 = {1};
        System.out.println(Arrays.toString(main.maxSlidingWindow(nums, 3)));
        System.out.println(Arrays.toString(main.maxSlidingWindow(nums2, 1)));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0 || k == 1) return nums;
        int n =  nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> mq = initQueue(nums, k);
        result[0] = nums[mq.peekFirst()];
        for (int i = k; i < n; i++) {
            int num = nums[i];
            if (!mq.isEmpty() && mq.peekFirst() <= i - k) {
                mq.removeFirst();
            }
            while (!mq.isEmpty() && num > nums[mq.peekLast()]) {
                mq.removeLast();
            }
            mq.addLast(i);
            result[i-k+1] = nums[mq.peekFirst()];
        }
        return result;
    }

    private Deque<Integer> initQueue(int[] nums, int k) {
        Deque<Integer> mq = new LinkedList();
        for (int i = 0; i < k; i++) {
            int num = nums[i];
            while (!mq.isEmpty() && num > nums[mq.peekLast()]) {
                mq.removeLast();
            }
            mq.addLast(i);
        }
        return mq;
    }
}
