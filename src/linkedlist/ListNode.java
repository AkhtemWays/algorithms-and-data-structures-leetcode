package linkedlist;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int val) { this.val = val; }

    public static void print(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }
}
