package arrays.SubstringWithLargestVariance;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println(largestVariance("ccbcbbb")); // 3
        System.out.println(largestVariance("abcde")); // 0
        System.out.println(largestVariance("aababbbaaaaa")); // 4
        System.out.println(largestVariance("jvvxxdeawjicdimejnydmbqdunruwgxysjhkumqufmdtlgubpw")); // 4
        System.out.println(largestVariance("ababab")); // 1
    }

    public static int largestVariance(String s) {
        if (s.length() < 2 || !checkTwoCharsArePresent(s)) return 0;

        return Math.max(calculate(s), calculate(new StringBuilder(s).reverse().toString()));
    }

    private static boolean checkTwoCharsArePresent(String s) {
        char ch = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != ch) return true;
        }
        return false;
    }

    private static int calculate(String s) {
        Set<Character> unique = new HashSet<>();
        int[] freqs = new int[26];
        for (int i = 0; i < s.length(); i++) {
            unique.add(s.charAt(i));
            freqs[s.charAt(i) - 'a']++;
        }
        char[] chars = new char[unique.size()];
        int k = 0;
        for (char ch : unique) chars[k++] = ch;

        int result = 0;
        for (int i = 0; i < chars.length-1; i++) {
            char a = chars[i];
            for (int j = i+1; j < chars.length; j++) {
                char b = chars[j];
                int freqA = freqs[a - 'a'];
                int freqB = freqs[b - 'a'];
                int res = 1;
                for (char ch : s.toCharArray()) {
                    if (ch == a) {
                        freqA--;
                    } else if (ch == b) {
                        freqB--;
                    }
                    if (freqA != freqs[a - 'a'] && freqB != freqs[b - 'a']) {
                        res = Math.max(res, Math.abs(freqA - freqB));
                    }
                }
                result = (freqA != freqs[a - 'a'] && freqB != freqs[b - 'a']) ? Math.max(result, res) : result;
                freqA = freqs[a - 'a'];
                freqB = freqs[b - 'a'];
                res = 1;
                for (int m = s.length()-1; m >=0; m--) {
                    char ch = s.charAt(m);
                    if (ch == a) {
                        freqA--;
                    } else if (ch == b) {
                        freqB--;
                    }
                    if (freqA != freqs[a - 'a'] && freqB != freqs[b - 'a']) {
                        res = Math.max(res, Math.abs(freqA - freqB));
                    }
                }
                result = (freqA != freqs[a - 'a'] && freqB != freqs[b - 'a']) ? Math.max(result, res) : result;
            }
        }
        return result;
    }
}
