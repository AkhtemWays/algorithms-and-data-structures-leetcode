package monotonic_queues_and_stacks.NumberofValidSubarrays;

import java.util.Stack;

public class Main {
    private static void test1(){
        int[] nums = {1,4,2,5,3};
        System.out.println(validSubarrays(nums));
    }
    private static void test2(){
        int[] nums = {3,2,1};
        System.out.println(validSubarrays(nums));
    }
    private static void test3(){
        int[] nums = {2,2,2};
        System.out.println(validSubarrays(nums));
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static int validSubarrays(int[] nums) {
        Stack<Integer> st = new Stack<>();
        st.add(-1);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            while (st.peek() != -1 && nums[st.peek()] > nums[i]) {
                int idx = st.pop();
                res += idx - st.peek() + 1;
            }
            st.add(i);
        }
        while (st.peek() != -1) {
            int idx = st.pop();
            res += idx - st.peek() + 1;
        }
        return res;
    }
}
