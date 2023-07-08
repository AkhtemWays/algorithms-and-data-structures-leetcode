package linkedlist.reorderTheList;

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
        reorderList(root);
        while (root != null) {
            System.out.println(root.val);
            root = root.next;
        }
    }

    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode cur = slow.next;
        slow.next = null;
        ListNode prev = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        ListNode end = prev;
        ListNode begin = head;

        while (end != null) {
            ListNode tmp1 = begin;
            ListNode tmp2 = end;
            begin = begin.next;
            end = end.next;
            tmp1.next = tmp2;
            tmp2.next = begin;
        }
    }
}
