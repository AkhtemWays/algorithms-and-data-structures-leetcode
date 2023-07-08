package arrays.houseRobber;

public class Main {
    public static void main(String[] args) {
        int[] nums1 = {2, 1, 1, 2};
        System.out.println(rob(nums1));
    }

    public static int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int evenMax = nums[0];
        int oddMax = nums[1];
        int maxSoFar = evenMax;

        for (int i = 2; i < nums.length; i++) {
            if (i % 2 == 0) {
                evenMax = maxSoFar + nums[i];
                maxSoFar = Math.max(maxSoFar, oddMax);
            } else {
                oddMax = maxSoFar + nums[i];
                maxSoFar = Math.max(maxSoFar, evenMax);
            }
        }

        return Math.max(evenMax, oddMax);
    }
}
