package linkedlist.ReverseLinkedListII;


import linkedlist.ListNode;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        ListNode newHead = main.reverseBetween(head, 4, 7);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
        ListNode head2 = new ListNode(3);
        head2.next = new ListNode(5);
        ListNode newHead2 = main.reverseBetween(head2, 1, 2);
        while (newHead2 != null) {
            System.out.println(newHead2.val);
            newHead2 = newHead2.next;
        }
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left >= right) return head;
        if (left == 1) return reverse(head, right);
        int count = 1;
        ListNode cur = head;
        ListNode prev = null;
        while (count++ < left) {
            prev = cur;
            cur = cur.next;
        }
        ListNode tail = reverse(cur, right - left + 1);
        if (prev != null) {
            prev.next = tail;
        }

        return head;
    }

    private ListNode reverse(ListNode head, int length) {
        if (head == null) return null;
        ListNode prev = null;
        ListNode cur = head;
        int count = 0;
        while (count++ < length && cur != null) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        head.next = cur;
        return prev;
    }
}
