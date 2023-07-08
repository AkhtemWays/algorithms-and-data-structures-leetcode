package stacks_and_queues.ImplementQueueusingStacks;

import java.util.Stack;

class MyQueue {
    private final Stack<Integer> pushStack;
    private final Stack<Integer> popStack;

    public MyQueue() {
        this.pushStack = new Stack<>();
        this.popStack = new Stack<>();
    }

    public void push(int x) {
        pushStack.add(x);
    }

    public int pop() {
        if (!popStack.isEmpty()) {
            return popStack.pop();
        }
        while (!pushStack.isEmpty()) {
            popStack.add(pushStack.pop());
        }
        return popStack.pop();
    }

    public int peek() {
        if (!popStack.isEmpty()) {
            return popStack.peek();
        }
        while (!pushStack.isEmpty()) {
            popStack.add(pushStack.pop());
        }
        return popStack.peek();
    }

    public boolean empty() {
        return pushStack.isEmpty() && popStack.isEmpty();
    }
}
