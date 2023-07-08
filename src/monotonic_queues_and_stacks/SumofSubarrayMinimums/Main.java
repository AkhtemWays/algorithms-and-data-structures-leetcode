package monotonic_queues_and_stacks.SumofSubarrayMinimums;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        int[] nums = {8, 6, 3, 5, 4, 9, 2};
        int[] nums2 = {11,81,94,43,3};
        int[] nums3 = {3,1,2,4};
        Main main = new Main();
        System.out.println(main.sumSubarrayMins(nums));
        System.out.println(main.sumSubarrayMins(nums2));
        System.out.println(main.sumSubarrayMins(nums3));
    }

    public int sumSubarrayMins(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int MOD = 10_000_007;
        long sum = 0;
        for (int i = 0; i <= nums.length; i++) {
            if (stack.isEmpty()) {
                stack.add(i);
                continue;
            }

            while (!stack.isEmpty() && (i == nums.length || nums[i] <= nums[stack.peek()])) {
                int mid = stack.pop();
                int leftBoundary = stack.isEmpty() ? -1 : stack.peek();
                int rightBoundary = i;
                int count = ((mid - leftBoundary) * (rightBoundary - mid)) % MOD;
                sum += (count * nums[mid]) % MOD;
                sum %= MOD;
            }
            stack.add(i);
        }
        return (int) sum % MOD;
    }
}
