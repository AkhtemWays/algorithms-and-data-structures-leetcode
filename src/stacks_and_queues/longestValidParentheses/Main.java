package stacks_and_queues.longestValidParentheses;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.longestValidParentheses(")()())"));
        System.out.println(main.longestValidParentheses("(()"));
        System.out.println(main.longestValidParentheses("()(()"));
        System.out.println(main.longestValidParentheses("(()()"));
    }

    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.add(i);
            }
            else if (s.charAt(i) == ')' && !stack.isEmpty()) {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.add(i);
                }
                max = Math.max(max, i - stack.peek());
            }
        }
        return max;
    }
}
