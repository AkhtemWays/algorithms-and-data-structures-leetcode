package linkedlist.mergekSortedLists;


import java.util.List;
import java.util.Stack;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode() { }
}

public class Main {
    public static void main(String[] args) {

    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        ListNode cur = mergeTwoLists(lists[0], lists[1]);
        for (int i = 2; i < lists.length; i++) {
            cur = mergeTwoLists(cur, lists[i]);
        }
        return cur;
    }

    private static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (list1 != null || list2 != null) {
            if (list1 == null || (list2 != null && list2.val <= list1.val)) {
                cur.next = list2;
                list2 = list2.next;
            }
            else {
                cur.next = list1;
                list1 = list1.next;
            }

            cur = cur.next;
        }
        return dummy.next;
    }
}
