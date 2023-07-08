package strings.ShortestPalindrome;

public class KnuttMorrisPrattApproach {
    public static void main(String[] args) {
        System.out.println(shortestPalindrome("aacecaaa"));
        System.out.println(shortestPalindrome("abcd"));
    }

    public static String shortestPalindrome(String s) {
        int[] lps = getLPS(s + "#" + new StringBuilder(s).reverse());
        StringBuilder res = new StringBuilder(s);
        res.reverse();
        for (int i = lps[lps.length-1]; i < s.length(); i++) {
            res.append(s.charAt(i));
        }
        return res.toString();
    }

    private static int[] getLPS(String s) {
        int[] lps = new int[s.length()];
        int prev = 0;
        int i = 1;
        while (i < s.length()) {
            if (s.charAt(prev) == s.charAt(i)) {
                lps[i++] = prev++ + 1;
            }
            else if (prev == 0) {
                lps[i++] = 0;
            }
            else {
                prev = lps[prev-1];
            }
        }
        return lps;
    }
}
