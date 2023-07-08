package linkedlist.addTwoNumbers;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode() {}
}
// 9,9,9,9,9,9,9
// 9999
// -> 8,9,9,9,0,0,0,1


public class Main {
    private static void test1() {
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        l1.next.next.next = new ListNode(9);
        l1.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next = new ListNode(9);
        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);
        ListNode root = addTwoNumbers(l1, l2);
        String expected = "89990001";
        System.out.println("test1");
        while (root != null) {
            System.out.print(root.val);
            root = root.next;
        }
        System.out.println();
    }
    private static void test2() {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode root = addTwoNumbers(l1, l2);
        int i = 0;
        String expected = "708";
        System.out.println("test2");
        while (root != null) {
            System.out.print(root.val);
            root = root.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (l1 != null || l2 != null || carry == 1) {
            int firstNum = 0;
            int secondNum = 0;
            if (l1 != null) {
                firstNum = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                secondNum = l2.val;
                l2 = l2.next;
            }
            int sum = firstNum + secondNum + carry;
            int remainder = sum % 10;
            carry = sum / 10;
            cur.next = new ListNode(remainder);
            cur = cur.next;
        }
        return dummy.next;
    }
}
