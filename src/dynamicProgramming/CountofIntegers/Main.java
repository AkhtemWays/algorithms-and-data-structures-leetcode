package dynamicProgramming.CountofIntegers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final int MOD = 100_000_000_7;
    public static void main(String[] args) {
        System.out.println(count("1", "12", 1, 8)); // 11
        System.out.println(count("4179205230", "7748704426", 8, 46)); // 883045899
    }


    public static int count(String num1, String num2, int min_sum, int max_sum) {
        int[][][] memo1 = new int[num1.length()+1][9*num1.length()][2];
        int[][][] memo2 = new int[num2.length()+1][9*num2.length()][2];
        for (int i = 0; i < memo1.length; i++) {
            for (int j = 0; j < memo1[i].length; j++) {
                Arrays.fill(memo1[i][j], -1);
            }
        }
        for (int i = 0; i < memo2.length; i++) {
            for (int j = 0; j < memo2[i].length; j++) {
                Arrays.fill(memo2[i][j], -1);
            }
        }
        return ((MOD + get(0, num2, min_sum, max_sum, 0, memo1, 1)) -
                get(0, num1.substring(0, num1.length()-1) + ((char) (num1.charAt(num1.length()-1) - 1)), min_sum, max_sum, 0, memo2, 1)) % MOD;
    }

    private static int get(int i, String n, int l, int r, int sumOfDigits, int[][][] memo, int prevSame) {
        if (i == n.length()) {
            if (sumOfDigits <= r && sumOfDigits >= l) {
                return 1;
            }
            return 0;
        }
        if (sumOfDigits > r) return 0;
        if (memo[i][sumOfDigits][prevSame] != -1) return memo[i][sumOfDigits][prevSame];
        int sum = 0;
        int N = Character.getNumericValue(n.charAt(i));
        if (prevSame == 1) {
            for (int digit = 0; digit < N; digit++) {
                sum = ((sum + get(i+1, n, l, r, sumOfDigits + digit, memo, 0) % MOD) % MOD) % MOD;
            }
            sum = ((sum + get(i+1, n, l, r, sumOfDigits + N, memo, 1) % MOD) % MOD) % MOD;
        } else {
            for (int digit = 0; digit < 10; digit++) {
                sum = ((sum + get(i+1, n, l, r, sumOfDigits + digit, memo, 0) % MOD) % MOD) % MOD;
            }
        }

        memo[i][sumOfDigits][prevSame] = sum % MOD;
        return sum % MOD;
    }
}
