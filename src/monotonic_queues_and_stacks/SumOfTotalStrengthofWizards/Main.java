package monotonic_queues_and_stacks.SumOfTotalStrengthofWizards;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1,3,1,2};
        int[] arr2 = {5,4,6};
        System.out.println(totalStrength(arr));
        System.out.println(totalStrength(arr2));
    }

    public static int totalStrength(int[] arr) {
        int MOD = 1000000007;

        Stack<Integer> stack = new Stack<>();
        long totalSum = 0;
        long sum = 0;
        List<Long> sums = new ArrayList<>();

        // building monotonically increasing stack
        for (int i = 0; i <= arr.length; i++) {
            if (i < arr.length) {
                sum += arr[i] % MOD;
                sums.add(sum);
            }

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
                long curSum = ((i-1 >= 0 ? sums.get(i-1) : 0) - (leftBoundary >= 0 ? sums.get(leftBoundary) : 0)) % MOD;

                // count of subarrays where mid is the minimum element
                long count = (mid - leftBoundary) * (i - mid) % MOD;
                long min = (count * arr[mid]) % MOD;
                totalSum += (curSum * min) % MOD;
                totalSum %= MOD;
            }
            stack.push(i);
        }

        return (int) (totalSum);
    }
}
