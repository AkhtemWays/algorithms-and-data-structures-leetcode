package stacks_and_queues.ImplementQueueusingStacks;

import java.util.Stack;

public class MyQueue2 {
    private final Stack<Integer> pushStack;
    private final Stack<Integer> popStack;

    public MyQueue2() {
        this.pushStack = new Stack<>();
        this.popStack = new Stack<>();
    }

    public void push(int x) {
        while (!popStack.isEmpty()) {
            this.pushStack.add(popStack.pop());
        }
        this.popStack.push(x);
        while (!pushStack.isEmpty()) {
            this.popStack.add(this.pushStack.pop());
        }
    }

    public int pop() {
        return this.popStack.pop();
    }

    public int peek() {
        return this.popStack.peek();
    }

    public boolean empty() {
        return popStack.isEmpty();
    }
}
