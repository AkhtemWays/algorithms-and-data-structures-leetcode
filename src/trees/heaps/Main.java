package trees.heaps;

import linkedlist.TreeNode;

public class Main {
    private static void test1() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        flatten(root);
        while (root != null) {
            System.out.println(root.val);
            root = root.right;
        }
    }
    private static void test2() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        flatten(root);
        while (root != null) {
            System.out.println(root.val);
            root = root.right;
        }
    }
    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void flatten(TreeNode root) {
        chain(root);
    }

    private static TreeNode chain(TreeNode node) {
        if (node == null) return null;
        if (node.left == null && node.right == null) return node;

        if (node.left != null) {
            TreeNode leftTail = chain(node.left);
            TreeNode rightTail = chain(node.right);
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
            return rightTail == null ? leftTail : rightTail;
        }

        return chain(node.right);
    }
}
