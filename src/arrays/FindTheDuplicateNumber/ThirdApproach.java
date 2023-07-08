package arrays.FindTheDuplicateNumber;

public class ThirdApproach {
    public static void main(String[] args) {
        int[] nums = {1, 3, 3, 2};
        int[] nums2 = {2,2,2,2,2};
        int[] nums3 = {1,3,4,2,2};
        System.out.println(findDuplicate(nums));
        System.out.println(findDuplicate(nums2));
        System.out.println(findDuplicate(nums3));
    }

    public static int findDuplicate(int[] nums) {
        int idx = 0;
        while (idx != nums[idx]) {
            int next = nums[idx];
            nums[idx] = idx;
            idx = next;
        }
        return idx;
    }
}
