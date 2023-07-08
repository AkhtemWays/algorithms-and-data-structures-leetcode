package linkedlist.AddTwoNumbersII;

import linkedlist.ListNode;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode result = main.addTwoNumbers(l1, l2);
        ListNode.print(result);

        ListNode head1 = new ListNode(2);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(3);
        ListNode head2 = new ListNode(5);
        head2.next = new ListNode(6);
        head2.next.next = new ListNode(4);
        ListNode result2 = main.addTwoNumbers(head1, head2);
        ListNode.print(result2);

        ListNode head3 = new ListNode(5);
        ListNode head4 = new ListNode(5);
        ListNode result3 = main.addTwoNumbers(head3, head4);
        ListNode.print(result3);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int[] lengths = findLengths(l1, l2);
        int diff = Math.abs(lengths[0] - lengths[1]);
        if (lengths[0] - lengths[1] > 0) return add(l1, l2, diff);
        return add(l2, l1, diff);
    }

    private ListNode add(ListNode l1, ListNode l2, int diff) {
        ListNode dummy = new ListNode(0);
        ListNode prev = new ListNode(0);
        dummy.next = prev;
        ListNode l1cur = l1;
        ListNode l2cur = l2;

        for (int i = 0; i < diff; i++) {
            prev = l1cur;
            l1cur = l1cur.next;
        }

        int carry = 0;
        while (l1cur != null && l2cur != null) {
            int sum = l1cur.val + l2cur.val;
            l1cur.val = sum % 10;
            carry = sum / 10;

            prev.val += carry;
            prev = l1cur;
            l1cur = l1cur.next;
            l2cur = l2cur.next;
        }
        if (dummy.next.val == 1) {
            dummy.next.next = l1;
            return dummy.next;
        }

        return l1;
    }

    private int[] findLengths(ListNode l1, ListNode l2) {
        int l1Length = 0;
        int l2Length = 0;
        ListNode l1cur = l1;
        ListNode l2cur = l2;
        while (l1cur != null || l2cur != null) {
            if (l1cur != null) {
                l1Length++;
                l1cur = l1cur.next;
            }
            if (l2cur != null) {
                l2Length++;
                l2cur = l2cur.next;
            }
        }
        return new int[]{l1Length, l2Length};
    }
}
