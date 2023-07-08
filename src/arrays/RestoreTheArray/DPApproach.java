package arrays.RestoreTheArray;

import java.util.Arrays;

public class DPApproach {
    private static final int MOD = 100_000_000_7;
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
        return dfs(0, s, k, new int[s.length()+1]) % MOD;
    }

    private static int dfs(int i, String s, int k, int[] dp) {
        if (i == s.length()) return 1;
        if (s.charAt(i) == '0') return 0;
        if (dp[i] != 0) return dp[i];
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        for (int j = i; j < s.length(); j++) {
            sb.append(s.charAt(j));
            if (isBiggerThanK(sb, k)) break;
            sum += dfs(i+1, s, k, dp);
        }
        dp[i] = sum;
        return sum;
    }

    private static boolean isBiggerThanK(StringBuilder sb, int k) {
        if (sb.charAt(0) == '0') return true;
        int value;
        try {
            value = Integer.parseInt(sb.toString());
        } catch (Exception e) {
            return true;
        }
        return value > k;
    }
}
