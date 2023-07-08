package linkedlist.InsertionSortList;

import linkedlist.ListNode;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        ListNode head = new ListNode(5);
        head.next = new ListNode(6);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(1);
        head.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next.next.next = new ListNode(4);
        ListNode newHead = main.insertionSortList(head);
        while (newHead != null) {
            System.out.print(newHead.val + " ");
            newHead = newHead.next;
        }
        System.out.println();

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(1);
        ListNode newHead2 = main.insertionSortList(head2);
        while (newHead2 != null) {
            System.out.print(newHead2.val + " ");
            newHead2 = newHead2.next;
        }
        System.out.println();

        ListNode head3 = new ListNode(3000);
        ListNode cur = head3;
        for (int val = 2999; val >= 0; val--) {
            cur.next = new ListNode(val);
            cur = cur.next;
        }
        ListNode newHead3 = main.insertionSortList(head3);
        while (newHead3 != null) {
            System.out.print(newHead3.val + " ");
            newHead3 = newHead3.next;
        }
    }

    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevA = dummy;
        ListNode curA = prevA.next;
        while (curA != null) {
            ListNode prevB = dummy;
            ListNode curB = prevB.next;
            while (curA.val >= curB.val && curA != curB) {
                prevB = curB;
                curB = curB.next;
            }
            if (curA == curB) {
                prevA = curA;
                curA = curA.next;
            } else {
                ListNode nextA = curA.next;
                prevB.next = curA;
                curA.next = curB;
                prevA.next = nextA;
            }
        }


        return dummy.next;
    }
}
