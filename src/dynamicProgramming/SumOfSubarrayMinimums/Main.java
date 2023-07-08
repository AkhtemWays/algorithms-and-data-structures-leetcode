package dynamicProgramming.SumOfSubarrayMinimums;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[] nums = {3, 1, 2, 4};
        int[] nums2 = {11,81,94,43,3};
        System.out.println(main.sumSubarrayMins(nums));
        System.out.println(main.sumSubarrayMins(nums2));
    }

    public int sumSubarrayMins(int[] arr) {
        int MOD = 1000000007;

        Stack<Integer> stack = new Stack<>();
        long sumOfMinimums = 0;

        // building monotonically increasing stack
        for (int i = 0; i <= arr.length; i++) {

            // when i reaches the array length, it is an indication that
            // all the elements have been processed, and the remaining
            // elements in the stack should now be popped out.

            while (!stack.empty() && (i == arr.length || arr[stack.peek()] >= arr[i])) {

                // Notice the sign ">=", This ensures that no contribution
                // is counted twice. rightBoundary takes equal or smaller
                // elements into account while leftBoundary takes only the
                // strictly smaller elements into account

                int mid = stack.pop();
                int leftBoundary = stack.empty() ? -1 : stack.peek();

                // count of subarrays where mid is the minimum element
                long count = (mid - leftBoundary) * (i - mid) % MOD;

                sumOfMinimums += (count * arr[mid]) % MOD;
                sumOfMinimums %= MOD;
            }
            stack.push(i);
        }

        return (int) (sumOfMinimums);
    }
}
