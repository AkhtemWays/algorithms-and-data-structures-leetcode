package linkedlist.palindromeLinkedList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode() {  }
}

public class Main {
    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(2);
        root.next.next.next = new ListNode(1);
        ListNode root2 = new ListNode(1);
        root2.next = new ListNode(2);
        System.out.println(isPalindrome(root));
        System.out.println(isPalindrome(root2));
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null) return false;
        int length = 1;
        StringBuilder sb = new StringBuilder();
        ListNode cur = head;
        while (cur != null) {
            sb.append(cur.val);
            cur = cur.next;
        }
        for (int i = 0; i < sb.length() / 2; i++) {
            if (sb.charAt(i) != sb.charAt(sb.length()-1-i)) return false;
        }
        return true;
    }
}
