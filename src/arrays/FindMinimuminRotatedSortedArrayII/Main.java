package arrays.FindMinimuminRotatedSortedArrayII;

public class Main {
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,-1,1,4}; // -1
        int[] nums2 = {1,3,5}; // 1
        int[] nums3 = {2,2,2,0,1}; // 0
        int[] nums4 = {1}; // 1
        int[] nums5 = {1, 1}; // 1
        int[] nums6 = {10,10,1,10,10}; // 1
        int[] nums7 = {2,2,2,0,2,2,2}; // 0
        System.out.println(findMin(nums));
        System.out.println(findMin(nums2));
        System.out.println(findMin(nums3));
        System.out.println(findMin(nums4));
        System.out.println(findMin(nums5));
        System.out.println(findMin(nums6));
        System.out.println(findMin(nums7));
    }

    public static int findMin(int[] nums) {
        if (nums[0] < nums[nums.length-1] || nums.length == 1) return nums[0];
        return nums[search(nums, 0, nums.length-1)];
    }

    private static int search(int[] nums, int l, int r) {
        if (l >= r) {
            return l + 1 < nums.length && nums[l+1] < nums[l] ? l + 1 : l;
        }
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (mid + 1 < nums.length && nums[mid] > nums[mid+1]) return mid+1;
            else if (nums[mid] >= nums[l] && nums[r] >= nums[mid] && r - l > 1) {
                int left = search(nums, l, mid-1);
                int right = search(nums, mid+1, r);
                return nums[left] < nums[right] ? left : right;
            }
            else if (nums[mid] >= nums[l]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        if (r < 0) return l;
        if (l >= nums.length) return r;
        return nums[l] < nums[r] ? l : r;
    }


}
