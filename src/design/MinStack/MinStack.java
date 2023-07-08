package design.MinStack;

import java.util.*;

public class MinStack {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // return -3
        minStack.pop();
        System.out.println(minStack.top());    // return 0
        System.out.println(minStack.getMin()); // return -2
    }
    private Stack<Integer> mins;
    private Stack<Integer> stack;

    public MinStack() {
        this.mins = new Stack<>();
        this.stack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (mins.isEmpty() || x <= mins.peek()) {
            mins.push(x);
        }
    }


    public void pop() {
        if (stack.peek().equals(mins.peek())) {
            mins.pop();
        }
        stack.pop();
    }

    public int top() {
        return this.stack.peek();
    }

    public int getMin() {
        return this.mins.peek();
    }
}
