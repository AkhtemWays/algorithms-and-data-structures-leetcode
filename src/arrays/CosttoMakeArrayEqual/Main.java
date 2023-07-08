package arrays.CosttoMakeArrayEqual;

public class Main {
    private static void test1() {
        int[] nums = {1,3,5,2};
        int[] cost = {2,3,1,14};
        System.out.println(minCost(nums, cost));
    }
    public static void main(String[] args) {
        test1();
    }

    public static long minCost(int[] nums, int[] cost) {
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
        for (int num : nums) {
            right = Math.max(num, right);
            left = Math.min(left, num);
        }

        long optimal = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long cur = calc(nums, cost, mid);
            long next = calc(nums, cost, mid+1);
            long smaller = Math.min(cur, next);
            optimal = Math.min(optimal, smaller);
            if (cur < next) {
                right = mid-1;
            } else {
                left = mid + 1;
            }
        }
        return optimal;
    }

    private static long calc(int[] nums, int[] cost, int target) {
        long answer = 0;
        for (int i = 0; i < nums.length; i++) {
            answer += (long)Math.abs(target - nums[i]) * cost[i];
        }
        return answer;
    }
}
