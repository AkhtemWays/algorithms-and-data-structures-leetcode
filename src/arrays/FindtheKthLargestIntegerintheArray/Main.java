package arrays.FindtheKthLargestIntegerintheArray;

public class Main {
    public static void main(String[] args) {
        String[] nums = {"3","6","7","10"};
        System.out.println(kthLargestNumber(nums, 4));
    }

    public static String kthLargestNumber(String[] nums, int k) {
        quickSelect(nums, k, 0, nums.length-1);
        return nums[k-1];
    }

    private static void quickSelect(String[] nums, int k, int l, int r) {
        if (l < r) {
            int partitionIndex = partition(l, r, nums);

            if (partitionIndex + 1 < k) {
                quickSelect(nums, k, 0, partitionIndex-1);
            } else if (partitionIndex + 1 > k) {
                quickSelect(nums, k, partitionIndex+1, r);
            }
        }
    }

    private static int partition(int l, int r, String[] nums) {
        int pivot = Integer.parseInt(nums[r]);
        int j = -1;
        for (int i = l; i < r; i++) {
            int num = Integer.parseInt(nums[i]);
            if (num > pivot) {
                swap(i, ++j, nums);
            }
        }
        swap(++j, r, nums);
        return j;
    }

    private static void swap(int i, int j, String[] nums) {
        String tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
