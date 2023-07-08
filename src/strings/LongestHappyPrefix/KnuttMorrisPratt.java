package strings.LongestHappyPrefix;

public class KnuttMorrisPratt {
    public static void main(String[] args) {
        System.out.println(longestPrefix("AACXAAAA"));
        System.out.println(longestPrefix("level"));
        System.out.println(longestPrefix("ababab"));
        System.out.println(longestPrefix("bba"));
    }

    public static String longestPrefix(String s) {
        int[] lcp = new int[s.length()];
        int prev = 0;
        int i = 1;
        while (i < s.length()) {
            if (s.charAt(prev) == s.charAt(i)) {
                lcp[i++] = prev++ + 1;
            }
            else if (prev == 0) {
                lcp[i++] = 0;
            }
            else {
                prev = lcp[prev-1];
            }
        }

        return s.substring(0, lcp[lcp.length-1]);
    }
}
