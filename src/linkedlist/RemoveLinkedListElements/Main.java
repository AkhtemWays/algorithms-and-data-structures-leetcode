package linkedlist.RemoveLinkedListElements;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);
        ListNode newHead = main.removeElements(head, 6);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode newHead = null;
        while (cur != null) {
            if (cur.val != val) {
                if (prev == null) {
                    prev = cur;
                    newHead = prev;
                } else {
                    prev.next = cur;
                    prev = prev.next;
                }
            }
            cur = cur.next;
        }
        if (prev != null && prev.next != null && prev.next.val == val) prev.next = null;

        return newHead;
    }
}
