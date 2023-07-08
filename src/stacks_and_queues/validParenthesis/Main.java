package stacks_and_queues.validParenthesis;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("("));
        System.out.println(isValid("  ) )"));
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '{' || ch == '(' || ch == '[') stack.add(ch);
            else if (ch == '}') {
                if (stack.isEmpty() || stack.pop() != '{') return false;
            }
            else if (ch == ']') {
                if (stack.isEmpty() || stack.pop() != '[') return false;
            }
            else if (ch == ')') {
                if (stack.isEmpty() || stack.pop() != '(') return false;
            }
        }
        return stack.isEmpty();
    }
}
