package bitManipulation.FindUniqueBinaryString;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class Main {
    private static void test1() {
        String[] strs = {"01","10"};
        System.out.println(findDifferentBinaryString(strs));
    }
    private static void test2() {
        String[] strs = {"00","01"};
        System.out.println(findDifferentBinaryString(strs));
    }
    private static void test3() {
        String[] strs = {"111","011","001"};
        System.out.println(findDifferentBinaryString(strs));
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < n; i++) numbers.add(Integer.parseInt(nums[i], 2));

        for (int num = 0; num < 1 << n; num++) {
            if (!numbers.contains(num)) {
                String res = Integer.toBinaryString(num);
                return "0".repeat(n-res.length()) + res;
            }
        }
        return "0".repeat(n);
    }
}
