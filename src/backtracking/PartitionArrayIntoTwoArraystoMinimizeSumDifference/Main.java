package backtracking.PartitionArrayIntoTwoArraystoMinimizeSumDifference;

import java.util.*;

public class Main {
    private static int answer;
    public static void main(String[] args) {
        int[] nums = {3,9,7,3};
        int[] nums2 = {-36,36};
        int[] nums3 = {2,-1,0,4,-2,-9};
        int[] nums4 = {76,8,45,20,74,84,28,1};
        int[] nums5 = {7772197,4460211,-7641449,-8856364,546755,-3673029,527497,-9392076,3130315,-5309187,-4781283,5919119,3093450,1132720,6380128,-3954678,-1651499,-7944388,-3056827,1610628,7711173,6595873,302974,7656726,-2572679,0,2121026,-5743797,-8897395,-9699694};
        System.out.println(minimumDifference(nums)); // 2
        System.out.println(minimumDifference(nums2)); // 72
        System.out.println(minimumDifference(nums3)); // 0
        System.out.println(minimumDifference(nums4)); // 2
        System.out.println(minimumDifference(nums5)); // 1
    }

    public static int minimumDifference(int[] nums) {
        int n = nums.length/2;
        long sum = Arrays.stream(nums).sum();
        HashMap<Integer, TreeSet<Integer>> left = generate(Arrays.copyOfRange(nums, 0, n));
        HashMap<Integer, TreeSet<Integer>> right = generate(Arrays.copyOfRange(nums, n, n*2));
        int answer = Integer.MAX_VALUE;
        for (int size : left.keySet()) {
            for (int leftSum : left.get(size)) {
                TreeSet<Integer> rightSums = right.get(n-size);
                int searchValue = (int) (sum / 2) - leftSum;
                Integer candidate1 = rightSums.ceiling(searchValue);
                Integer candidate2 = rightSums.floor(searchValue);
                if (candidate1 != null) {
                    answer = (int) Math.min(answer, Math.abs(sum - 2L * (leftSum + candidate1)));
                }
                if (candidate2 != null) {
                    answer = (int)Math.min(answer, Math.abs(sum - 2L * (leftSum + candidate2)));
                }
            }
        }
        return answer;
    }

    private static HashMap<Integer, TreeSet<Integer>> generate(int[] nums) {
        int n = nums.length;
        HashMap<Integer, TreeSet<Integer>> sums = new HashMap<>();
        for (int bitmask = 0; bitmask < 1 << n; bitmask++) {
            int size = 0;
            int i = 0;
            int x = bitmask;
            int sum = 0;
            while (x > 0) {
                if ((x & 1) == 1) {
                    sum += nums[i];
                    size++;
                }
                i++;
                x >>= 1;
            }
            sums.computeIfAbsent(size, (k) -> new TreeSet<>()).add(sum);
        }
        return sums;
    }
}
