package arrays.jumpGame2;

public class Main {
    public static void main(String[] args) {
        int[] nums1 = {4,3,1,1,4};
        int[] nums2 = {3,3,0,1,4};
        int[] nums3 = {1, 1, 1, 1};
        int[] nums4 = {2,1,1,1,1};
        System.out.println(jump(nums1));
        System.out.println(jump(nums2));
        System.out.println(jump(nums3));
        System.out.println(jump(nums4));
    }

    public static int jump(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return 0;
        return countJumps(0, nums);
    }

    private static int countJumps(int start, int[] nums) {
        if (nums[start] >= nums.length - 1 - start) return 1;
        int optimalJumpIdx = -1;
        int maxJump = -1;
        for (int i = start + 1; i <= start + nums[start]; i++) {
            int maybeMaxJump = nums[i] + i - start;
            if (maybeMaxJump > maxJump) {
                maxJump = maybeMaxJump;
                optimalJumpIdx = i;
            }
        }
        return 1 + countJumps(optimalJumpIdx, nums);
    }
}
