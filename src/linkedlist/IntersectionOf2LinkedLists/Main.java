package linkedlist.IntersectionOf2LinkedLists;

import java.util.HashSet;
import java.util.Set;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode() {}
}

public class Main {
    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(9);
        list1.next.next = new ListNode(1);
        ListNode list2 = new ListNode(3);
        list2.next = new ListNode(2);
        list1.next.next.next = list2.next;
        list2.next.next = new ListNode(4);
        list1.next.next.next.next = list2.next.next;
        ListNode intersection = getIntersectionNode(list1, list2);
        System.out.println(intersection.val);
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int countA = 0;
        int countB = 0;
        ListNode curA = headA;
        ListNode curB = headB;
        while (curA != null || curB != null) {
            if (curA != null) {
                countA++;
                curA = curA.next;
            }
            if (curB != null) {
                countB++;
                curB = curB.next;
            }
        }
        int diff = Math.abs(countA - countB);
        curA = headA;
        curB = headB;
        for (int i = 0; i < diff; i++) {
            if (countA > countB) curA = curA.next;
            else curB = curB.next;
        }

        while (curA != curB) {
            curA = curA.next;
            curB = curB.next;
        }
        return curA;
    }
}
