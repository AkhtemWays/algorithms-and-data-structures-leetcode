package dynamicProgramming.longestValidParentheses;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.longestValidParentheses(")()())"));
        System.out.println(main.longestValidParentheses("(()"));
        System.out.println(main.longestValidParentheses("()(()"));
        System.out.println(main.longestValidParentheses("(()()"));
    }

    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i-1) == '(' && s.charAt(i) == ')') {
                dp[i] = (i - 2 >= 0 ? dp[i-2] : 0) + 2;

            } else if (s.charAt(i) == ')' && s.charAt(i-1) == ')') {
                if (i - dp[i-1] - 1 >= 0 && s.charAt(i - dp[i-1] - 1) == '(') {
                    dp[i] = dp[Math.max(i - dp[i - 1] - 2, 0)] + dp[i-1] + 2;
                }
            }
            max = Math.max(dp[i], max);
        }

        return max;
    }
}
