package monotonic_queues_and_stacks.NextGreaterElementII;

import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,3,2,1};
        int[] nums2 = {1,1,1,1,1};
        Main main = new Main();
        System.out.println(Arrays.toString(main.nextGreaterElements(nums)));
        System.out.println(Arrays.toString(main.nextGreaterElements(nums2)));
    }

    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        for (int i = 0; i < nums.length; i++) {
            while (!st.isEmpty() && nums[st.peek()] < nums[i]) {
                int idx = st.pop();
                res[idx] = nums[i];
            }
            st.add(i);
        }
        if (!st.isEmpty()) {
            for (int i = 0; i < nums.length; i++) {
                while (!st.isEmpty() && nums[st.peek()] < nums[i]) {
                    int idx = st.pop();
                    res[idx] = nums[i];
                }
            }
        }
        return res;
    }
}
