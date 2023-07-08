package arrays.OnesandZeroes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] strings = {"10","0","1"}; // 2
        String[] strings2 = {"10","0001","111001","1","0"}; // 4
        String[] strings3 = {"11111","100","1101","1101","11000"}; // 3
        System.out.println(findMaxForm(strings, 1, 1));
        System.out.println(findMaxForm(strings2, 5, 3));
        System.out.println(findMaxForm(strings3, 5, 7));
    }

    private static int k;

    public static int findMaxForm(String[] strs, int m, int n) {
        k = strs.length;

        int[][] pairs = new int[k][3];
        for (int i = 0; i < k; i++) {
            String str = strs[i];
            int zeros = 0, ones = 0;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '1') ones++;
                else zeros++;
            }
            pairs[i] = new int[]{zeros, ones, i};
        }

        int[][] zerosSorted = Arrays.copyOf(pairs, k);
        int[][] onesSorted = Arrays.copyOf(pairs, k);
        Arrays.sort(zerosSorted, (a, b) -> {
            if (a[0] == 0 && b[0] == 0) return a[1] - b[1];
            if (a[0] == 0) return 1;
            if (b[0] == 0) return -1;
            return a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0];
        });
        Arrays.sort(onesSorted, (a, b) -> {
            if (a[1] == 0 && b[1] == 0) return a[0] - b[0];
            if (a[1] == 0) return 1;
            if (b[1] == 0) return -1;
            return a[1] - b[1] == 0 ? a[0] - b[0] : a[1] - b[1];
        });

        return Math.max(calculate(zerosSorted, onesSorted, m, n), calculate(onesSorted, zerosSorted, m, n));
    }

    private static int calculate(int[][] pairsPrimary, int[][] pairsSecondary, int m, int n) {
        List<Integer> l = new ArrayList<>();
        int subsets = 0;
        int zeros = 0, ones = 0;
        boolean[] taken = new boolean[k];
        for (int[] pair : pairsPrimary) {
            if (zeros + pair[0] <= m && ones + pair[1] <= n) {
                zeros += pair[0];
                ones += pair[1];
                subsets++;
                taken[pair[2]] = true;
            }
        }
        for (int[] pair : pairsSecondary) {
            if (zeros + pair[0] <= m && ones + pair[1] <= n && !taken[pair[2]]) {
                zeros += pair[0];
                ones += pair[1];
                subsets++;
            }
        }
        return subsets;
    }
}
