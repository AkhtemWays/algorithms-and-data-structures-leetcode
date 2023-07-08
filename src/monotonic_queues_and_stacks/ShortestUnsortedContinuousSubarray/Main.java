package monotonic_queues_and_stacks.ShortestUnsortedContinuousSubarray;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        int[] nums = {2,6,4,8,10,9,15};
        int[] nums2 = {1,2,3,4};
        int[] nums3 = {1};
        int[] nums4 = {1, 2, 3, 3, 3};
        int[] nums5 = {1,3,2,2,2};
        int[] nums6 = {1,3,2,4,5};
        System.out.println(findUnsortedSubarray(nums));
        System.out.println(findUnsortedSubarray(nums2));
        System.out.println(findUnsortedSubarray(nums3));
        System.out.println(findUnsortedSubarray(nums4));
        System.out.println(findUnsortedSubarray(nums5));
        System.out.println(findUnsortedSubarray(nums6));
    }

    public static int findUnsortedSubarray(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int lowerBound = -1;
        for (int i = 0; i < nums.length; i++) {
            if (st.isEmpty() || nums[i] >= nums[st.peek()]) {
                st.add(i);
                continue;
            }
            while (!st.isEmpty() && nums[i] < nums[st.peek()]) {
                st.pop();
            }
            lowerBound = st.isEmpty() ? 0 : st.peek()+1;
            break;
        }
        if (lowerBound == -1) return 0;
        int upperBound = -1;
        st = new Stack<>();
        for (int i = nums.length - 1; i >= lowerBound; i--) {
            if (st.isEmpty() || nums[st.peek()] >= nums[i]) {
                st.add(i);
                continue;
            }
            break;
        }
        while (!st.isEmpty() && nums[lowerBound] > nums[st.peek()]) {
            st.pop();
        }
        if (st.isEmpty()) {
            upperBound = nums.length - 1;
        } else {
            upperBound = st.peek() - 1;
        }

        return upperBound - lowerBound + 1;
    }
}
