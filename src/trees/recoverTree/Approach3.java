package trees.recoverTree;

import linkedlist.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Approach3 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.right.right = new TreeNode(5);
        recoverTree(root);
        inorderPrint(root);
    }
//         3
//        / \
//       4   2
//      /     \
//     1       5
    public static void swap(TreeNode a, TreeNode b) {
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }

    public static void recoverTree(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode x = null, y = null, pred = null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();
            if (pred != null && root.val < pred.val) {
                y = root;
                if (x == null) x = pred;
                else break;
            }
            pred = root;
            root = root.right;
        }

        swap(x, y);
    }

    private static void inorderPrint(TreeNode node) {
        if (node == null) return;

        inorderPrint(node.left);
        System.out.println(node.val);
        inorderPrint(node.right);
    }
}
