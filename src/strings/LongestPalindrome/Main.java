package strings.LongestPalindrome;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println((int)'a');
    }

    public static int longestPalindrome(String s) {
        HashMap<Character, Integer> counts = new HashMap<>();
        int res = 0;
        for (char ch : s.toCharArray()) {
            counts.put(ch, counts.getOrDefault(ch, 0) + 1);
        }
        boolean flag = false;
        for (int val : counts.values()) {
            res += ((val / 2) * 2);
            if (val == 1) {
                flag = true;
            }
        }
        return flag || counts.size() == 1 ? res + 1 : res;
    }
}
