package monotonic_queues_and_stacks.Pattern132;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1,0,1,-4,-3};
        System.out.println(find132pattern(nums));
    }

    public static boolean find132pattern(int[] nums) {
        if (nums.length < 3) return false;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            if (st.isEmpty() || (st.size() == 2 && nums[i] < st.peek()) || (st.size() == 1 && nums[i] > st.peek())) {
                st.add(nums[i]);
                if (st.size() == 3) return true;
                continue;
            }
            if (st.size() == 2 && st.peek() > nums[i] && nums[i] > st.get(0)) {
                st.pop();
                st.add(nums[i]);
            }
            else if (st.size() == 1 && st.peek() > nums[i]) {
                st.pop();
                st.add(nums[i]);
            }
        }
        if (st.size() == 3) return true;
        return false;
    }
}
