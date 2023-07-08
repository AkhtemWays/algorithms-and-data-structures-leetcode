package linkedlist.mergeTwoSortedLinkedLists;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode() {  }
}

public class Main {
    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);
        ListNode root = mergeTwoLists(list1, list2);
        while (root != null) {
            System.out.println(root.val);
            root = root.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
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
