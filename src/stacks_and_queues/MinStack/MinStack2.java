package stacks_and_queues.MinStack;

import java.util.Iterator;
import java.util.Stack;

class Node {
    int value;
    int minValue;
    Node(int value, int minValue) {
        this.value = value;
        this.minValue = minValue;
    }
}

public class MinStack2 {
    private final Stack<Node> st;
    public MinStack2() {
        this.st = new Stack<>();
    }

    public void push(int val) {
        int minValue = Math.min(val, st.isEmpty() ? Integer.MIN_VALUE : st.peek().minValue);
        st.push(new Node(val, minValue));
    }

    public void pop() {
        st.pop();
    }

    public int top() {
        return st.peek().value;
    }

    public int getMin() {
        return st.peek().minValue;
    }
}
