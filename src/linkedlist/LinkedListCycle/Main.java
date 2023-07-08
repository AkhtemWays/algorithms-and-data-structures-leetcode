package linkedlist.LinkedListCycle;

class ListNode {
    int val;
    ListNode next;
    int pos;
    ListNode(int val) { this.val = val; }
    ListNode() {}
}

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
//        head.next.next.next.next = head.next;
        System.out.println(main.hasCycle(head));
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode tortoise = head;
        ListNode hare = head.next;
        while ((hare != null && hare.next != null) && tortoise != hare) {
            tortoise = tortoise.next;
            hare = hare.next.next;
        }
        return tortoise == hare;
    }
}
