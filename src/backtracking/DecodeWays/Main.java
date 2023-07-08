package backtracking.DecodeWays;

import java.util.*;

public class Main {
    private final Set<Character> chars = new HashSet<>(List.of('0', '1', '2', '3', '4', '5', '6'));
    public static void main(String[] args) {
        dynamicProgramming.DecodeWays.Main main = new dynamicProgramming.DecodeWays.Main();
        String s = "126";
        String s2 = "1";
        String s3 = "10";
        String s4 = "106";
        System.out.println(main.numDecodings(s));
        System.out.println(main.numDecodings(s2));
        System.out.println(main.numDecodings(s3));
        System.out.println(main.numDecodings(s4));
    }

    Map<Integer, Integer> memo = new HashMap<>();

    public int numDecodings(String s) {
        return recursiveWithMemo(0, s);
    }

    private int recursiveWithMemo(int index, String str) {
        // Have we already seen this substring?
        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        // If you reach the end of the string
        // Return 1 for success.
        if (index == str.length()) {
            return 1;
        }

        // If the string starts with a zero, it can't be decoded
        if (str.charAt(index) == '0') {
            return 0;
        }

        if (index == str.length() - 1) {
            return 1;
        }


        int ans = recursiveWithMemo(index + 1, str);
        if (Integer.parseInt(str.substring(index, index + 2)) <= 26) {
            ans += recursiveWithMemo(index + 2, str);
        }

        // Save for memoization
        memo.put(index, ans);

        return ans;
    }
}
