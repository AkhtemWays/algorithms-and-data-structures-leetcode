package arrays.threeSumClosest;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int result = threeSumClosest(nums, 1);
        System.out.println(result);
    }

    public static int threeSumClosest(int[] nums, int target) {
        int[] cleaned = Arrays.stream(nums).sorted().toArray();
        int result = cleaned[0] + cleaned[1] + cleaned[cleaned.length - 1];
        for (int i = 0; i < cleaned.length - 1; i++) {
            int a = cleaned[i];
            if (i > 0 && a == cleaned[i - 1]) continue;
            int j = i + 1;
            int k = cleaned.length - 1;
            while (k > j) {
                int b = cleaned[j];
                int c = cleaned[k];
                int sum = a + b + c;
                if (sum < target) j += 1;
                else k -= 1;
                if (Math.abs(sum - target) < Math.abs(result - target)) result = sum;
            }
        }
        return result;
    }
}
