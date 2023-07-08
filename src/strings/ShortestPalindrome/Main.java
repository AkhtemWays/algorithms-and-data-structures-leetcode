package strings.ShortestPalindrome;

public class Main {
    public static void main(String[] args) {
        System.out.println(shortestPalindrome("aacecaaa"));
        System.out.println(shortestPalindrome("abcd"));
        System.out.println(shortestPalindrome("aba"));
        System.out.println(shortestPalindrome("ba"));
        System.out.println(shortestPalindrome("abbacd"));
        System.out.println(shortestPalindrome("abbbba"));
        System.out.println(shortestPalindrome("aabba")); // abbaabba
    }

    public static String shortestPalindrome(String s) {
        if (s.length() <= 1) return s;
        Boolean[] dp = new Boolean[s.length()];
        dp[0] = true;
        int optimal = s.length() % 2 == 0 && s.charAt(0) == s.charAt(1) ? s.length()-2 : s.length()-1;
        for (int i = 0; i <= s.length()/2; i++) {
            if (canFinishEven(i, s, dp)) {
                optimal = Math.min(optimal, s.length() - 2 * (i + 1));
            }
            if (canFinishOdd(i, s, dp)) {
                optimal = Math.min(optimal, s.length() - 1 - 2 * i);
            }
        }
        return getResult(s, optimal);
    }

    private static String getResult(String s, int howMany) {
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < howMany; k++) {
            sb.append(s.charAt(s.length()-1-k));
        }
        sb.append(s);
        return sb.toString();
    }

    private static boolean canFinishEven(int start, String s, Boolean[] dp) {
        int left = start;
        int right = start+1;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (dp[left] != null) {
                dp[start] = dp[left];
                return dp[left];
            }
            left--;
            right++;
        }
        return left < 0;
    }

    private static boolean canFinishOdd(int start, String s, Boolean[] dp) {
        int left = start-1;
        int right = start+1;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (dp[left] != null) {
                dp[start] = dp[left];
                return dp[left];
            }
            left--;
            right++;
        }
        return left < 0;
    }
}
