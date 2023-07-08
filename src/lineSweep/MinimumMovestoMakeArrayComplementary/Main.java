package lineSweep.MinimumMovestoMakeArrayComplementary;

public class Main {
    public static void main(String[] args) {
        int[] nums1 = {1,2,4,3};
        int[] nums2 = {1,2,2,1};
        int[] nums3 = {1,2,1,2};
        System.out.println(minMoves(nums1, 4)); // 1
        System.out.println(minMoves(nums2, 2)); // 2
        System.out.println(minMoves(nums3, 2)); // 0
    }

    public static int minMoves(int[] nums, int limit) {
        int[] sums = new int[2 + limit * 2];
        int[] segments = new int[2 + limit * 2];
        int n = nums.length;
        int maxSum = 2;
        for (int i = 0; i < n/2; i++) {
            int a = nums[i];
            int b = nums[n-i-1];
            int sum = a + b;
            if (sum > maxSum) {
                maxSum = sum;
            }
            int min = Math.min(a, b) + 1;
            int max = Math.max(a, b) + limit;
            segments[min]--;
            segments[max+1]++;
            sums[sum]++;
        }

        int current = n;
        int best = n;
        for (int sum = 2; sum <= maxSum; sum++) {
            current += segments[sum];
            best = Math.min(best, current - sums[sum]);
        }
        return best;
    }
}
