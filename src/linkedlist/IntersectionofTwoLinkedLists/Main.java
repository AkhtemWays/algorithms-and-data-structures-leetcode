package linkedlist.IntersectionofTwoLinkedLists;

import linkedlist.ListNode;

public class Main {
    public static void main(String[] args) {
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(9);
        headA.next.next = new ListNode(1);
        ListNode intersection = new ListNode(2);
        intersection.next = new ListNode(4);
        headA.next.next.next = intersection;
        ListNode headB = new ListNode(3);
        headB.next = intersection;
        ListNode maybeIntersection = getIntersectionNode(headA, headB);
        while (maybeIntersection != null) {
            System.out.println(maybeIntersection.val);
            maybeIntersection = maybeIntersection.next;
        }
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int countA = count(headA);
        int countB = count(headB);
        int diff = Math.abs(countA - countB);
        if (countA < countB) {
            while (diff-- > 0) {
                headB = headB.next;
            }
        } else {
            while (diff-- > 0) {
                headA = headA.next;
            }
        }

        while (headA != null && headB != null) {
            if (headA == headB) return headA;
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    private static int count(ListNode headA) {
        int count = 0;
        while (headA != null) {
            count++;
            headA = headA.next;
        }
        return count;
    }
}
