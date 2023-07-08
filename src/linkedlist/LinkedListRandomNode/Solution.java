package linkedlist.LinkedListRandomNode;

import linkedlist.ListNode;

import java.util.*;

public class Solution {
    ListNode head;
    Random random = new Random();
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        Solution solution = new Solution(head);
        System.out.println(solution.getRandom()); // return 1
        System.out.println(solution.getRandom()); // return 1
        System.out.println(solution.getRandom()); // return 1
        System.out.println(solution.getRandom()); // return 1
        System.out.println(solution.getRandom()); // return 1
    }

    public Solution(ListNode head) {
        this.head = head;
    }

    public int getRandom() {
        int i = 1;
        int value = 0;
        ListNode cur = this.head;
        while (cur != null) {
            if (Math.random() < 1.0 / i) {
                value = cur.val;
            }
            cur = cur.next;
        }
        return value;
    }
}
