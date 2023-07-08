package linkedlist.SplitLinkedListInPart;

import linkedlist.ListNode;

public class Main {
    private static void print(ListNode[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            printNode(nodes[i]);
            System.out.println();
        }
    }
    private static void printNode(ListNode node) {
        ListNode cur = node;
        while (cur != null) {
            System.out.print(cur.val + " ,");
            cur = cur.next;
        }
    }
    public static void main(String[] args) {
        ListNode l = new ListNode(1);
        l.next = new ListNode(2);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(4);
        l.next.next.next.next = new ListNode(5);
        l.next.next.next.next.next = new ListNode(6);
        l.next.next.next.next.next.next = new ListNode(7);
        l.next.next.next.next.next.next.next = new ListNode(8);
        l.next.next.next.next.next.next.next.next = new ListNode(9);
        l.next.next.next.next.next.next.next.next.next = new ListNode(10);
        print(splitListToParts(l, 3));

    }

    public static ListNode[] splitListToParts(ListNode head, int k) {
        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }

        ListNode[] result = new ListNode[k];
        cur = head;
        int remainder = length % k;
        for (int i = 0; i < k; i++) {
            int n = length / k;
            if (remainder > 0) {
                n++;
                remainder--;
            }
            ListNode[] meta = getList(cur, n);
            if (meta != null) {
                result[i] = meta[0];
                cur = meta[1];
            } else {
                return result;
            }
        }
        return result;
    }

    private static ListNode[] getList(ListNode head, int n) {
        if (n == 0 || head == null) return null;
        ListNode cur = head;
        while (--n > 0) {
            cur = cur.next;
        }
        ListNode tail = cur.next;
        cur.next = null;
        return new ListNode[]{head, tail};
    }
}
