package linkedlist.PlusOneLinkedList;

import linkedlist.ListNode;

import java.util.Stack;

public class Main {

    public void print(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        9 9 9 9 -> 1 0 0 0 0
//        2 9 9 9 9 -> 3 0 0 0 0
//        1 9 9 0 8 9 9 9 9 1 -> 1 9 9 0 8 9 9 9 9 2
//        1 2 9 -> 1 3 0
        Main main = new Main();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(9);
        main.print(main.plusOne(head));

        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(9);
        head2.next.next.next = new ListNode(9);
        head2.next.next.next.next = new ListNode(9);
        main.print(main.plusOne(head2));
    }



    public ListNode plusOne(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy.next;
        ListNode slow = dummy;
        while (fast != null) {
            if (fast.val != 9) {
                slow = fast;
            }
            fast = fast.next;
        }
        slow.val++;
        while (slow.next != null) {
            slow = slow.next;
            slow.val = 0;
        }
        if (dummy.val == 1) return dummy;
        return dummy.next;
    }
}
