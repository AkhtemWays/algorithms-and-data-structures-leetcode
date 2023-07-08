package linkedlist.ReverseTheLinkedList;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public class Main {
    public static void main(String[] args) {
        ListNode list = new ListNode(1);
        list.next = new ListNode(2);
        list.next.next = new ListNode(3);
        list.next.next.next = new ListNode(4);
        list.next.next.next.next = new ListNode(5);
        ListNode head = reverseListIterative(list);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        return reverse(head, head.next, true);
    }

    private static ListNode reverse(ListNode head, ListNode tail, boolean initial) {
        if (tail == null) return head;

        ListNode tmp = tail.next;
        tail.next = head;
        if (initial) head.next = null;
        return reverse(tail, tmp, false);
    }

    public static ListNode reverseListIterative(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;
        while (cur != null) {
            ListNode tail = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tail;
        }

        return prev;
    }
}
