package greedy.MinimizeDeviationinArray;

import java.util.Arrays;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int[] nums = {908,357,331,698}; // 123
        int[] nums2 = {399,908,648,357,693,502,331,649,596,698}; // 315
        int[] nums3 = {3,5}; // 1
        int[] nums4 = {1,2,3,4};
        int[] nums5 = {4,1,5,20,3};
        int[] nums6 = {2,10,8};
        System.out.println(minimumDeviation(nums)); // 123
        System.out.println(minimumDeviation(nums2)); // 315
        System.out.println(minimumDeviation(nums3)); // 1
        System.out.println(minimumDeviation(nums4)); // 1
        System.out.println(minimumDeviation(nums5)); // 3
        System.out.println(minimumDeviation(nums6)); // 3
    }

    public static int minimumDeviation(int[] nums) {
        TreeSet<Long> bst = new TreeSet<>();
        for (long num : nums) bst.add(num);

        long deviation = Long.MAX_VALUE;
        while (bst.last() - bst.first() < deviation) {
            deviation = Math.min(deviation, bst.last() - bst.first());
            while (bst.last() % 2 == 0 && Math.abs((bst.last() / 2) - bst.first()) < Math.abs(bst.last() - bst.first())) {
                long max = bst.last();
                bst.remove(max);
                bst.add(max / 2);
                deviation = Math.min(deviation, bst.last() - bst.first());
            }
            while (bst.first() % 2 == 1 && Math.abs(bst.first() * 2 - bst.last()) < Math.abs(bst.last() - bst.first())) {
                long min = bst.first();
                bst.remove(min);
                bst.add(min * 2);
                deviation = Math.min(deviation, bst.last() - bst.first());
            }
        }
        return (int) deviation;
    }
}
