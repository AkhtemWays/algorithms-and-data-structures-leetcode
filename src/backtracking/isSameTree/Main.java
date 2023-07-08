package backtracking.isSameTree;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
}

public class Main {
    public static void main(String[] args) {
//        linkedlist.TreeNode node1 = new linkedlist.TreeNode(2);
//        node1.left = new linkedlist.TreeNode(1);
//        node1.right = new linkedlist.TreeNode(3);
//        node1.right.right = new linkedlist.TreeNode(4);
//
//        linkedlist.TreeNode node2 = new linkedlist.TreeNode(2);
//        node2.left = new linkedlist.TreeNode(1);
//        node2.right = new linkedlist.TreeNode(3);
//        node2.right.right = new linkedlist.TreeNode(4);
//        System.out.println(isSameTree(node1, node2));

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);
        System.out.println(isSymmetric(root));
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        return checkSameness(p, q);
    }

    public static boolean checkSameness(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null) return false;

        if (node1.val == node2.val) {
            return checkSameness(node1.left, node2.left) && checkSameness(node1.right, node2.right);
        } else {
            return false;
        }
    }

    public static boolean isSymmetric(TreeNode root) {
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left != null && right != null && left.val == right.val) return checkSymmetricity(left.right, right.left) && checkSymmetricity(left.left, right.right);
        return left == null && right == null;
    }

    public static boolean checkSymmetricity(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;

        if (left.val == right.val)
            return checkSymmetricity(left.right, right.left) && checkSymmetricity(left.left, right.right);
        return false;
    }
}
