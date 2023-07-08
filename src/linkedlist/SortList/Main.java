package linkedlist.SortList;


import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
    int pos;
    ListNode(int val) { this.val = val; }
    ListNode() {}
}

public class Main {
    public static void main(String[] args) {
        ListNode root = new ListNode(4);
        root.next = new ListNode(2);
        root.next.next = new ListNode(1);
        root.next.next.next = new ListNode(3);
        ListNode head = sortList(root);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        List<ListNode> nodes = new ArrayList<>();
        while (cur != null) {
            nodes.add(cur);
            cur = cur.next;
        }
        nodes.sort(Comparator.comparingInt(a -> a.val));
        ListNode newHead = nodes.get(0);
        ListNode tail = nodes.get(nodes.size() - 1);
        ListNode ptr = newHead;
        tail.next = null;
        for (int i = 1; i < nodes.size(); i++) {
            ptr.next = nodes.get(i);
            ptr = ptr.next;
        }
        return newHead;
    }


}
