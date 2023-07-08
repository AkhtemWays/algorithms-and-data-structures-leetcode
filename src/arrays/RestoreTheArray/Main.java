package arrays.RestoreTheArray;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(numberOfArrays("120189", 200)); // 8
        System.out.println(numberOfArrays("101022", 100)); // 2
        System.out.println(numberOfArrays("1000", 10000)); // 1
        System.out.println(numberOfArrays("1317", 2000)); // 8
        System.out.println(numberOfArrays("1000", 10)); // 0
        System.out.println(numberOfArrays("1234", 40)); // 5
        System.out.println(numberOfArrays("123", 40)); // 3
        System.out.println(numberOfArrays("1234560", 60)); // 8
        System.out.println(numberOfArrays("12345670", 70)); // 13
        System.out.println(numberOfArrays("171895851301603621199279559472582240564804526335544534392551", 905)); // 573330896
        System.out.println(numberOfArrays("407780786171321121429620765476840275495357129574174233567552131453038760763182952432348422252546559691171161181440370120987634895458140447952079749439961325982629462531738374032416182281868731817661954890417245087359968833257550123324827240537957646002494771036413572415", 823924906)); // 573330896
    }

    public static int numberOfArrays(String s, int k) {
        int MOD = 100_000_000_7;
        int[] dp = new int[s.length()+1];
        if (s.isEmpty() || s.charAt(0) == '0') return 0;
        dp[0] = 1;
        int left = 0;
        int right = 0;
        while (right < s.length() && left <= right) {
            if (isBiggerThanK(s, left, right, k)) {
                left++;
            } else {
                dp[right+1] = getSum(dp, left, right, s) % MOD;
                right++;
            }
        }
        return left > right ? 0 : dp[s.length()];
    }

    private static int getSum(int[] dp, int left, int right, String s) {
        int MOD = 100_000_000_7;
        int sum = 0;
        for (int i = left; i <= right; i++) {
            if (s.charAt(i) != '0') sum = (sum + dp[i]) % MOD;
        }
        return sum % MOD;
    }

    private static boolean isBiggerThanK(String s, int left, int right, int k) {
        if (s.charAt(left) == '0') return true;
        StringBuilder sb = new StringBuilder();
        for (int i = left; i <= right; i++) {
            sb.append(s.charAt(i));
        }
        int value;
        try {
            value = Integer.parseInt(sb.toString());
        } catch (Exception e) {
            return true;
        }
        return value > k;
    }
}
