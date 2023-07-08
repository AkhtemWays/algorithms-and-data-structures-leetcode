package arrays.NondecreasingArray;

import java.util.Stack;

public class Main {
    private static void test1() {
        int[] nums = {3, 4, 2, 3};
        System.out.println(checkPossibility(nums));
    }
    private static void test2() {
        int[] nums = {4, 2, 3};
        System.out.println(checkPossibility(nums));
    }
    private static void test3() {
        int[] nums = {4,2,1};
        System.out.println(checkPossibility(nums));
    }
    private static void test4() {
        int[] nums = {5,7,1,8};
        System.out.println(checkPossibility(nums));
    }
    public static void main(String[] args) {
        test1(); // false
        test2(); // true
        test3(); // false
        test4(); // true
    }

    public static boolean checkPossibility(int[] nums) {
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] > nums[i+1]) {
                int prev = nums[i];
                nums[i] = nums[i+1];
                boolean isIncreasing = increasing(nums);
                if (isIncreasing) return true;
                nums[i] = prev;
                nums[i+1] = nums[i];
                return increasing(nums);
            }
        }
        return true;
    }

    private static boolean increasing(int[] nums) {
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] > nums[i+1]) return false;
        }
        return true;
    }
}
