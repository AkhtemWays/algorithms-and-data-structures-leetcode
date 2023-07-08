package linkedlist.LinkedListCycleII;

import java.util.HashSet;
import java.util.Set;

class ListNode {
    int val;
    ListNode next;
    int pos;
    ListNode(int val) { this.val = val; }
    ListNode() {}
}

public class Main {
    public static void main(String[] args) {
        ListNode root = new ListNode(3);
        root.next = new ListNode(2);
        root.next.next = new ListNode(0);
        root.next.next.next = new ListNode(-4);
        root.next.next.next.next = root.next;
        System.out.println(detectCycle(root).val);
    }

    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return head;
        if (head.next == head) return null;
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast.next != null && fast.next.next != null) fast = fast.next.next;
            else return null;

            slow = slow.next;
        }
        ListNode cur = head;
        Set<Integer> s = new HashSet<>();
        while (!s.contains(cur.val)) {
            s.add(cur.val);
            cur = cur.next;
        }
        return cur;
    }
}
