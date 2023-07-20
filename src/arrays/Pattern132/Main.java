package arrays.Pattern132;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static void test1() {
        int[] nums = {1,2,3,4};
        System.out.println(find132pattern(nums));
    }
    private static void test2() {
        int[] nums = {3,1,4,2};
        System.out.println(find132pattern(nums));
    }
    private static void test3() {
        int[] nums = {-1,3,2,0};
        System.out.println(find132pattern(nums));
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static boolean find132pattern(int[] nums) {
        List<Integer> indices = new ArrayList<>();
        indices.add(0);
        for (int i = 1; i < nums.length; i++) {
            int size = indices.size();
            if (nums[i] < nums[indices.get(size - 1)]) {
                if (size == 1 || (nums[i] > nums[indices.get(size-2)])) {
                    indices.set(size-1, i);
                }
            } else if (nums[i] > indices.get(size - 1)) {
                indices.add(i);
            }
        }

        for (int i = 1, left = indices.get(0); i < indices.size(); i++) {
            int right = indices.get(i);
            int pattern2 = nums[right];
            for (int j = left + 1; j < right; j++) {
                if (nums[j] > pattern2) return true;
            }
            left = right;
        }

        return false;
    }
}
