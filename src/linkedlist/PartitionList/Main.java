package linkedlist.PartitionList;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode() {  }
}

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        ListNode newHead = main.partition(head, 3);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }

    public ListNode partition(ListNode head, int x) {
        ListNode leftDummy = new ListNode(0);
        ListNode rightDummy = new ListNode(0);
        ListNode left = leftDummy;
        ListNode right = rightDummy;
        while (head != null) {
            if (head.val < x) {
                left.next = new ListNode(head.val);
                left = left.next;
            } else {
                right.next = new ListNode(head.val);
                right = right.next;
            }
            head = head.next;
        }
        left.next = rightDummy.next;
        return leftDummy.next;
    }
}
