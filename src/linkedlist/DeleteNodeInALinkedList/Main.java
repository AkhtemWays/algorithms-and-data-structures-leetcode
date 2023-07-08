package linkedlist.DeleteNodeInALinkedList;

import linkedlist.ListNode;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        main.deleteNode(head.next);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public void deleteNode(ListNode node) {
        ListNode next = node.next;
        node.val = next.val;
        node.next = next.next;
    }
}
