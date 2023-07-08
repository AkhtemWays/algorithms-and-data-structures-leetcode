package arrays.FindtheeofAllPrefixesofanArray;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] input = {2,3,7,5,10};
        System.out.println(Arrays.toString(findPrefixScore(input)));
    }

    public static long[] findPrefixScore(int[] nums) {
        int max = Integer.MIN_VALUE;
        long sum = 0L;
        long[] answer = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            int curSum = nums[i] + max;
            sum += curSum;
            answer[i] = sum;
        }

        return answer;
    }
}
