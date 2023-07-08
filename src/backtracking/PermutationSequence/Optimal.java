package backtracking.PermutationSequence;

import java.util.ArrayList;
import java.util.List;

public class Optimal {
    public static void main(String[] args) {
        System.out.println(getPermutation(3, 3)); // 213
        System.out.println(getPermutation(4, 9));
    }

    private static int[] factorials;
    public static String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) nums.add(i);

        if (factorials == null) {
            factorials = new int[10];
            factorials[0] = 1;
            int factorial = 1;
            for (int i = 1; i < 10; i++) {
                factorials[i] = factorial *= i;
            }
        }
        k--;

        int c = n-1;
        StringBuilder sb = new StringBuilder();
        while (c >= 0) {
            int idx = k / factorials[c];
            sb.append(nums.get(idx));
            nums.remove(idx);
            k %= factorials[c];
            c--;
        }
        return sb.toString();
    }
}
