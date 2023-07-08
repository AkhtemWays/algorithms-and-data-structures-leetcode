package strings.ConcatenationofConsecutiveBinaryNumbers;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        System.out.println(concatenatedBinary(12));
    }

    public static int concatenatedBinary(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(Integer.toBinaryString(i));
        }
        int MOD = 100_000_000_7;
        int res = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(sb.length()-1-i) == '1') {
                res = (res + (1 << i)) % MOD;
            }
        }
        return res;
    }
}
