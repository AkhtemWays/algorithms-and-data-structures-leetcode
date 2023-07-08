package math.evaluateReversePolishNotation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String[] tokens1 = {"2","1","+","3","*"}; // ((2 + 1) * 3) = 9
        String[] tokens2 = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"}; // ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
//        String[] tokens3 = {"+", "-", "/", "14", "5", "1", "17"};
        System.out.println(evalRPN(tokens1));
        System.out.println(evalRPN(tokens2));
//        System.out.println(evalRPN(tokens3));
    }

    public static int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        stack.push(tokens[0]);
        Set<String> ops = new HashSet<>(List.of("+", "-", "/", "*"));
        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            if (ops.contains(token)) {
                int right = Integer.parseInt(stack.pop());
                int left = Integer.parseInt(stack.pop());
                switch (token) {
                    case "*":
                        stack.push(String.valueOf(left * right));
                        break;
                    case "-":
                        stack.push(String.valueOf(left - right));
                        break;
                    case "/":
                        stack.push(String.valueOf(left / right));
                        break;
                    case "+":
                        stack.push(String.valueOf(left + right));
                        break;
                }
            } else {
                stack.push(token);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
