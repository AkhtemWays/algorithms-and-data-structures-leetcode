package linkedlist.ConvertSortedListToBinarySearchTree;

import linkedlist.ListNode;
import linkedlist.TreeNode;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        ListNode head = new ListNode(-10);
        head.next = new ListNode(-3);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(9);
        TreeNode root = main.sortedListToBST(head);
        System.out.println();

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        TreeNode root2 = main.sortedListToBST(head2);
        System.out.println();

        ListNode head3 = new ListNode(-1);
        head3.next = new ListNode(0);
        head3.next.next = new ListNode(1);
        head3.next.next.next = new ListNode(2);
        TreeNode root3 = main.sortedListToBST(head3);
    }

    private ListNode head;

    private int findSize(ListNode head) {
        ListNode ptr = head;
        int c = 0;
        while (ptr != null) {
            ptr = ptr.next;
            c += 1;
        }
        return c;
    }

    private TreeNode convertListToBST(int l, int r) {
        // Invalid case
        if (l > r) {
            return null;
        }

        int mid = (l + r) / 2;

        // First step of simulated inorder traversal. Recursively form
        // the left half
        TreeNode left = this.convertListToBST(l, mid - 1);

        // Once left half is traversed, process the current node
        TreeNode node = new TreeNode(this.head.val);
        node.left = left;

        // Maintain the invariance mentioned in the algorithm
        this.head = this.head.next;

        // Recurse on the right hand side and form BST out of them
        node.right = this.convertListToBST(mid + 1, r);
        return node;
    }

    public TreeNode sortedListToBST(ListNode head) {
        // Get the size of the linked list first
        int size = this.findSize(head);

        this.head = head;

        // Form the BST now that we know the size
        return convertListToBST(0, size - 1);
    }
}
