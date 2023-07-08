package linkedlist.MaxStack;

import java.util.Comparator;
import java.util.TreeSet;

class ListNode {
    public int val;
    public ListNode next;
    public ListNode prev;
    int order;
    public ListNode(int val, int order) { this.val = val; this.order = order;}
}

public class MaxStack {
    private ListNode tail;
    private final TreeSet<ListNode> bst;
    private int order;
    public MaxStack() {
        tail = null;
        bst = new TreeSet<>((a, b) -> a.val - b.val == 0 ? a.order - b.order : a.val - b.val);
        order = 0;
    }

    public void push(int x) {
        ListNode node = new ListNode(x, ++order);
        if (tail == null) {
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = tail.next;
        }
        bst.add(node);
    }

    public int pop() {
        if (tail == null) {
            return -1;
        }
        int value = tail.val;
        if (bst.size() == 1) {
            bst.remove(tail);
            tail = null;
        } else {
            ListNode newTail = tail.prev;
            bst.remove(tail);
            newTail.next = null;
            tail = newTail;
        }
        return value;
    }

    public int top() {
        return tail.val;
    }

    public int peekMax() {
        return bst.last().val;
    }

    public int popMax() {
        if (bst.isEmpty()) {
            return -1;
        }
        ListNode node = bst.last();
        int val = node.val;
        if (bst.size() == 1) {
            tail = null;
        }
        else if (node.prev == null) {
            node.next.prev = null;
        }
        else if (node.next == null) {
            node.prev.next = null;
            tail = node.prev;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        bst.remove(node);
        return val;
    }

    private static void test1() {
        MaxStack stk = new MaxStack();
        stk.push(5);   // [5] the top of the stack and the maximum number is 5.
        stk.push(1);   // [5, 1] the top of the stack is 1, but the maximum is 5.
        stk.push(5);   // [5, 1, 5] the top of the stack is 5, which is also the maximum, because it is the top most one.
        System.out.println(stk.top());     // return 5, [5, 1, 5] the stack did not change.
        System.out.println(stk.popMax());  // return 5, [5, 1] the stack is changed now, and the top is different from the max.
        System.out.println(stk.top());     // return 1, [5, 1] the stack did not change.
        System.out.println(stk.peekMax()); // return 5, [5, 1] the stack did not change.
        System.out.println(stk.pop());     // return 1, [5] the top of the stack and the max element is now 5.
        System.out.println(stk.top());     // return 5, [5] the stack did not change.
    }

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    private static void test2() {
        MaxStack stk = new MaxStack();
        stk.push(5);
        System.out.println(stk.peekMax()); // 5
        System.out.println(stk.popMax()); // 5
    }

    private static void test3() {
        MaxStack stk = new MaxStack();
        stk.push(5);
        System.out.println(stk.peekMax());
        System.out.println(stk.pop());
    }

}
