package trees.simpleBinaryTree.DiameterofBinaryTree;


class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }

public class Main {
    public static void main(String[] args) {

    }

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        return getHeight(root.left) + getHeight(root.right);
    }

    private static int getHeight(TreeNode node) {
        if (node == null) return 0;

        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }
}
