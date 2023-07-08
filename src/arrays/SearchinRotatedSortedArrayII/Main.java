package arrays.SearchinRotatedSortedArrayII;

public class Main {
    public static void main(String[] args) {
        int[] nums = {2,5,6,0,0,1,2};
        int[] nums2 = {1,0,1,1,1};
        System.out.println(search(nums, 3));
        System.out.println(search(nums2, 0));
    }

    public static boolean search(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        return search(nums, target, l, r);
    }

    public static boolean search(int[] nums, int target, int l, int r) {
        if (l == r) return nums[l] == target;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] >= nums[l] && nums[r] >= nums[mid]) {
                return search(nums, target, l, mid) || search(nums, target, mid+1, r);
            }
            else if (nums[mid] >= nums[l]) {
                if (target <= nums[mid] && target >= nums[l]) {
                    return binarySearch(nums, target, l, mid+1);
                }
                l = mid + 1;
            }
            else {
                if (target <= nums[r] && target >= nums[mid]) {
                    return binarySearch(nums, target, mid, r+1);
                }
                r = mid - 1;
            }
        }
        return false;
    }

    static boolean binarySearch(int[] nums, int target, int l, int r) {
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[m] < target) l = m + 1;
            else r = m;
        }
        return nums[l] == target;
    }
}
