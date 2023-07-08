package linkedlist.rotateRight;

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
 }

public class Main {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);
        ListNode rotatedNode = rotateRight(head, 1);
        while (rotatedNode != null) {
            System.out.println(rotatedNode.val);
            rotatedNode = rotatedNode.next;
        }
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) return head;
        // find length and tail
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            length++;
            tail = tail.next;
        }

        k = k % length;
        if (k == 0) return head;
        int iterationLength = length - k;
        ListNode cutNode = head;
        for (int i = 1; i < iterationLength; i++) {
            cutNode = cutNode.next;
        }
        ListNode returnNode = cutNode.next;
        cutNode.next = null;
        tail.next = head;

        return returnNode;
    }
}
