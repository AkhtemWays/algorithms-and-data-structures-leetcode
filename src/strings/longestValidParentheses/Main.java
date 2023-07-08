package strings.longestValidParentheses;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String s1 = "(()"; // 2
        String s2 = ")()())"; // 4
        String s3 = ""; // 0
        String s4 = "()(())"; // 6
        String s5 = "()(()"; // 2
        String s6 = "()((()))"; // 8
        String s7 = "()((())"; // 4
        String s8 = "()()())((()))"; // 6
        String s9 = "(()(((()"; // 4
        String s10 = "(()(((()"; // 2
//        System.out.println(longestValidParentheses(s1));
//        System.out.println(longestValidParentheses(s2));
//        System.out.println(longestValidParentheses(s3));
//        System.out.println(longestValidParentheses(s4));
//        System.out.println(longestValidParentheses(s5));
//        System.out.println(longestValidParentheses(s6));
//        System.out.println(longestValidParentheses(s7));
//        System.out.println(longestValidParentheses(s8));
//        System.out.println(longestValidParentheses(s9));
    }

    public static int longestValidParentheses(String s) {
        int leftCount = 0;
        int rightCount = 0;
        int max = 0;
        int curMax = 0;
        for (char ch : s.toCharArray()) {
            if (ch == ')') rightCount++;
            else leftCount++;

            if (rightCount == leftCount) {
                curMax += leftCount;
                leftCount = 0;
                rightCount = 0;
            }
            if (rightCount > leftCount) {
                max = Math.max(curMax, max);
                curMax = 0;
                rightCount = 0;
                leftCount = 0;
            }

        }
        max = Math.max(max, Math.max(curMax, rightCount));
        return max*2;
    }

    public static int algorithm(String s) {
        Stack<Integer> stack = new Stack();
        stack.push(-1);
        int maxLen = 0;
        for(int i = 0; i < s.length(); i++)
        {
            if(s.charAt(i) == '(')
                stack.push(i);
            else if(s.charAt(i) == ')')
            {
                stack.pop();
                if(stack.empty())
                    stack.push(i);
                else
                    maxLen = Math.max(maxLen, i - stack.peek());
            }
        }
        return maxLen;
    }
}
