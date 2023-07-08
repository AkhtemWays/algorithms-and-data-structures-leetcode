package linkedlist.RemoveDuplicatesFromSortedList;

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
        ListNode root = new ListNode(1);
        root.next = new ListNode(1);
        root.next.next = new ListNode(1);
        root.next.next.next = new ListNode(2);
        root.next.next.next.next = new ListNode(2);
        root.next.next.next.next.next = new ListNode(2);
        ListNode updatedRoot = main.deleteDuplicates(root);
        while (updatedRoot != null) {
            System.out.println(updatedRoot.val);
            updatedRoot = updatedRoot.next;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode slow = head;
        ListNode next = head.next;
        while (next != null) {
            if (next.val != slow.val) {
                slow.next = next;
                slow = slow.next;
            }
            next = next.next;
        }
        slow.next = null;
        return head;
    }
}
