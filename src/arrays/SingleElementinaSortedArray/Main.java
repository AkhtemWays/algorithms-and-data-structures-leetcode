package arrays.SingleElementinaSortedArray;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1,1,2,3,3,4,4,8,8};
        int[] nums2 = {3,3,7,7,10,11,11};
        System.out.println(singleNonDuplicate(nums));
        System.out.println(singleNonDuplicate(nums2));
    }

    public static int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length-1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int val = nums[mid];
            boolean isEven = mid % 2 == 0;
            if (mid+1 < nums.length && nums[mid+1] == val) {
                if (isEven) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            else if (mid-1 >= 0 && nums[mid-1] == val) {
                if (isEven) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            else return nums[mid];
        }
        return nums[l];
    }
}
