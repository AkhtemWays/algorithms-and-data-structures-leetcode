package arrays.MinimumWindowSubsequence;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.minWindow("bbbbbbbbde", "bde")); // bde
        System.out.println(main.minWindow("abbdebdde", "bde")); // bde
        System.out.println(main.minWindow("abcdebdde", "bde")); // bcde
        System.out.println(main.minWindow("bbddddee", "bde")); // bdddde
        System.out.println(main.minWindow("jmeqksfrsdcmsiwvaovztaqenprpvnbstl", "k"));
        System.out.println(main.minWindow("bbbbbbbde", "bbbde")); // bbbde
        System.out.println(main.minWindow("cnhczmccqouqadqtmjjzl", "mm")); // mccqouqadqtm
    }

    public String minWindow(String s1, String s2) {
        if (s2.length() > s1.length() || s2.length() == 0) return "";
        if (s2.length() == 1) {
            String str = String.valueOf(s2.charAt(0));
            return s1.contains(str) ? str : "";
        }
        int count = 0;
        int k = 0;
        while (k+1 < s2.length() && s2.charAt(k) == s2.charAt(k+1)) {
            k++;
            count++;
        }
        k++;
        count++;
        char firstChar = s2.charAt(0);
        String initial = String.valueOf(firstChar).repeat(count);
        boolean containsFull = s1.contains(initial);

        int i = -1;
        int j = count;
        List<Integer> startPositions = new ArrayList<>();
        while (++i < s1.length()) {
            while (i < s1.length() && s1.charAt(i) == firstChar) {
                j--;
                i++;
            }
            if (j <= 0 || (j < count && s2.length() == count)) startPositions.add(i);
            j = count;
        }
        initial = containsFull ? initial : String.valueOf(s2.charAt(0));
        String res = s1 + "dsa";
        for (int startPosition : startPositions) {
            StringBuilder sb = new StringBuilder(initial);
            for (int l = startPosition, c = containsFull ? k : 1; l < s1.length() && c < s2.length(); l++) {
                sb.append(s1.charAt(l));
                if (s1.charAt(l) == s2.charAt(c)) {
                    c++;
                }
                if (c == s2.length()) {
                    if (sb.toString().length() < res.length()) {
                        res = sb.toString();
                        if (res.length() == s2.length()) return res;
                    }
                    break;
                }
            }
        }
        return res.length() > s1.length() ? "" : res;
    }
}
