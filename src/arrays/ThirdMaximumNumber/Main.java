package arrays.ThirdMaximumNumber;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }

    public static int thirdMax(int[] nums) {
        if (nums.length == 0) return -1;
        int firstMax = Arrays.stream(nums).max().getAsInt();
        int secondMax = Integer.MIN_VALUE;
        int c2 = 0;
        for (int num : nums) {
            if (num > secondMax && num != firstMax) {
                secondMax = num;
            }
        }
        if (secondMax == Integer.MIN_VALUE) return firstMax;

        int thirdMax = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > thirdMax && num != firstMax && num != secondMax) {
                thirdMax = num;
            }
        }
        if (thirdMax == Integer.MIN_VALUE) return firstMax;
        return thirdMax;
    }
}
