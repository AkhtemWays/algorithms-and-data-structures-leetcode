package strings.RabinKarp;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(patternSearch("abtabcydabc", "abc"));
    }

    public static List<Integer> patternSearch(String s, String pattern) {
        int prime = 100_000_000_9;
        int base = 26;
        long patternHash = getHash(pattern, 0, pattern.length()-1);
        long hash = getHash(s, 0, pattern.length()-1);
        List<Integer> res = new ArrayList<>();
        if (patternHash == hash && areIdentical(s, 0, pattern.length()-1, pattern)) {
            res.add(0);
        }
        for (int i = pattern.length(); i < s.length(); i++) {
            hash -= ((s.charAt(i-pattern.length()) - 'a' + 1) * Math.pow(base, pattern.length()-1)) % prime;
            hash = (hash * base) % prime;
            hash += s.charAt(i) - 'a' + 1;
            if (patternHash == hash && areIdentical(s, i-pattern.length()+1, i, pattern)) {
                res.add(i-pattern.length()+1);
            }
        }
        return res;
    }

    private static long getHash(String s, int start, int end) {
        int prime = 100_000_000_9;
        long hash = 0;
        int base = 26;
        for (int i = start; i <= end; i++) {
            hash = (hash + ((s.charAt(i) - 'a' + 1) * ((long) Math.pow(base, end-i)))) % prime;
        }
        return hash;
    }

    private static boolean areIdentical(String s, int start, int end, String pattern) {
        for (int i = start, k = 0; i <= end; i++, k++){
            if (s.charAt(i) != pattern.charAt(k)) return false;
        }
        return true;
    }
}
