package arrays.CollectingChocolates;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }

    public static long minCost(int[] nums, int x) {
        int n = nums.length;
        int best = Arrays.stream(nums).sum();
        int[] mins = Arrays.copyOf(nums, n);
        for (int i = 0; i < nums.length; i++) {
            int cost = i * x;
            int sum = 0;
            for (int j = 0; j < nums.length; j++) {
                mins[j] = Math.min(mins[j], nums[(i + j) % n]);
                sum += mins[j];
            }
            best = Math.min(sum, best + cost);
        }
        return best;
    }
}
