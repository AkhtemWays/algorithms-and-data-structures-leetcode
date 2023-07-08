package arrays.FindTheDuplicateNumber;

public class TortoiseAndHare {
    public static void main(String[] args) {
        int[] nums = {1, 3, 3, 2};
        int[] nums2 = {2,2,2,2,2};
        int[] nums3 = {1,3,4,2,2};
        System.out.println(findDuplicate(nums));
        System.out.println(findDuplicate(nums2));
        System.out.println(findDuplicate(nums3));
    }

    public static int findDuplicate(int[] nums) {
        int tortoise = nums[0];
        int hare = nums[0];

        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Find the "entrance" to the cycle.
        tortoise = nums[0];

        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }

        return hare;
    }
}
