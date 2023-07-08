package stacks_and_queues.basicCalculator2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String s = "1 - 1 + 1";
        System.out.println(calculate(s));
    }

    public static int calculate(String s) {
        s = s.replaceAll("[ ]", "");
        String[] nums = s.split("[+-/*]");
        String[] operators = s.split("[0-9]+");
        List<String> everything = new ArrayList<>();
        everything.add(nums[0]);
        Stack<String> stack = new Stack<>();
        for (int i = 1; i < nums.length; i++) {
            everything.add(operators[i]);
            everything.add(nums[i]);
        }
        int i = 0;
        while (i < everything.size()) {
            String cur = everything.get(i);
            if (cur.equals("/") || cur.equals("*")) {
                Integer left = Integer.parseInt(stack.pop());
                Integer right = Integer.parseInt(everything.get(i+1));
                String operator = cur;
                Integer res = operator.equals("/") ? left / right : left * right;
                cleanStack(stack, res);
                i+=2;
            } else {
                stack.add(cur);
                i++;
            }
        }
        if (stack.size() == 1) return Integer.parseInt(stack.pop());
        int res = Integer.parseInt(stack.pop());
        cleanStack(stack, res);
        return Integer.parseInt(stack.pop());
    }

    public static void cleanStack(Stack<String> stack, Integer result) {
        while (!stack.isEmpty()) {
            String operator = stack.pop();
            Integer left = Integer.parseInt(stack.pop());
            result = operator.equals("+") ? left + result : left - result;
        }
        stack.add(result.toString());
    }
}
