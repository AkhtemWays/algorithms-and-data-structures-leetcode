package linkedlist.DeleteNNodesAfterMNodesOfaLinkedList;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode() { }
}

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        ListNode next = head.next.next.next.next.next;
        next.next = new ListNode(7);
        next.next.next = new ListNode(8);
        next.next.next.next = new ListNode(9);
        next.next.next.next.next = new ListNode(10);
        next.next.next.next.next.next = new ListNode(11);
//        next.next.next.next.next.next.next = new ListNode(12);
        ListNode newHead = main.deleteNodes(head, 1, 3);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }

    public ListNode deleteNodes(ListNode head, int m, int n) {
        ListNode cur = head;
        ListNode prev = new ListNode(0);
        int mCounter = m;
        int nCounter = n;
        while (cur != null) {
            if (mCounter > 0) {
                prev.next = cur;
                prev = prev.next;
                mCounter--;
            } else {
                nCounter--;
            }
            cur = cur.next;
            if (mCounter == 0 && nCounter == 0) {
                mCounter = m;
                nCounter = n;
            }
        }
        prev.next = null;

        return head;
    }
}

