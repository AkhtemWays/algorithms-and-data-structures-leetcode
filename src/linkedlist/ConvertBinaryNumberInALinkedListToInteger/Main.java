package linkedlist.ConvertBinaryNumberInALinkedListToInteger;

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
        head.next = new ListNode(0);
        head.next.next = new ListNode(0);
        System.out.println(main.getDecimalValue(head));
    }

    public int getDecimalValue(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            head = head.next;
        }
        int result = 0;
        for (int i = sb.length() - 1; i >= 0; i--) {
            result += Character.getNumericValue(sb.charAt(sb.length() - 1 - i)) << i;
        }
        return result;
    }
}
