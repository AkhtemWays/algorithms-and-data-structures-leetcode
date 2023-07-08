package linkedlist.OddEvenLinkedList;

import linkedlist.ListNode;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode newHead = main.oddEvenList(head);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return head;
        ListNode dummyOdd = new ListNode(0);
        ListNode odd = head;
        dummyOdd.next = odd;

        ListNode dummyEven = new ListNode(0);
        ListNode even = odd.next;
        dummyEven.next = even;

        ListNode cur = even.next;
        even.next = null;
        boolean oddTurn = true;
        while (cur != null) {
            if (oddTurn) {
                ListNode tmp = cur.next;
                odd.next = cur;
                odd = odd.next;
                cur = tmp;
            } else {
                ListNode tmp = cur.next;
                even.next = cur;
                even = even.next;
                even.next = null;
                cur = tmp;
            }
            oddTurn = !oddTurn;
        }
        odd.next = dummyEven.next;
        return dummyOdd.next;
    }
}
