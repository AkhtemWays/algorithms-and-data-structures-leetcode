package arrays.FindTheDuplicateNumber;

public class SecondApproach {
    public static void main(String[] args) {
        int[] nums = {1, 3, 3, 2};
        int[] nums2 = {2,2,2,2,2};
        int[] nums3 = {1,3,4,2,2};
        System.out.println(findDuplicate(nums));
        System.out.println(findDuplicate(nums2));
        System.out.println(findDuplicate(nums3));
    }

    public static int findDuplicate(int[] nums) {
        return replace(0, nums);
    }

    private static int replace(int idx, int[] nums) {
        if (idx == nums[idx]) return nums[idx];
        int next = nums[idx];
        nums[idx] = idx;
        return replace(next, nums);
    }
}
