package linkedlist.swapPairs;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public class Main {
    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);
        ListNode result = swapPairs(root);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        ListNode next = cur.next;
        ListNode result = cur.next;
        ListNode prev = null;

        while (cur != null && next != null) {
            cur.next = next.next;
            next.next = cur;
            if (prev != null) prev.next = next;
            prev = cur;
            cur = cur.next;
            if (cur != null) next = cur.next;
        }
        return result;
    }
}
