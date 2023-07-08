package stacks_and_queues.MinStack;

import java.util.Iterator;
import java.util.Stack;

public class MinStack {
    private final Stack<Integer> minStack;
    private final Stack<Integer> casualStack;
    public MinStack() {
        this.casualStack = new Stack<>();
        this.minStack = new Stack<>();
    }

    public void push(int val) {
        this.casualStack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.add(val);
        }
    }

    public void pop() {
        int value = this.casualStack.pop();
        if (!minStack.isEmpty() && value == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return this.casualStack.peek();
    }

    public int getMin() {
        if (!minStack.isEmpty()) {
            return minStack.peek();
        }
        Iterator<Integer> it = casualStack.iterator();
        while (it.hasNext()) {
            Integer value = it.next();
            if (minStack.isEmpty() || minStack.peek() >= value) {
                minStack.add(value);
            }
        }
        return minStack.peek();
    }
}
