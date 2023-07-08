package stacks_and_queues.RemoveAllAdjacentDuplicatesinStringII;

import java.util.Iterator;
import java.util.Stack;

public class Main {
    static class CharWithCount {
        int count;
        char ch;
        CharWithCount(int count, char ch) {
            this.count = count;
            this.ch = ch;
        }
    }
    public static void main(String[] args) {
//        System.out.println(removeDuplicates("abcd", 2));
        System.out.println(removeDuplicates("deeedbbcccbdaa", 3));
    }

    public static String removeDuplicates(String s, int k) {
        Stack<CharWithCount> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (stack.isEmpty() || stack.peek().ch != ch) {
                stack.add(new CharWithCount(1, ch));
            }
            else {
                if (stack.peek().count == k - 1) {
                    while (!stack.isEmpty() && stack.peek().ch == ch) {
                        stack.pop();
                    }
                } else {
                    stack.add(new CharWithCount(stack.peek().count + 1, ch));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        Iterator<CharWithCount> it = stack.iterator();
        while (it.hasNext()) {
            sb.append(it.next().ch);
        }
        return sb.toString();
    }
}
