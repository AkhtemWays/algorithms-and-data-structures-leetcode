package linkedlist;

public class TreeNode implements Cloneable {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
    }


    @Override
    public TreeNode clone() {
        return clone(this);
    }

    private static TreeNode clone(TreeNode node) {
        if (node == null) return null;

        TreeNode copy = new TreeNode(node.val);
        copy.left = clone(node.left);
        copy.right = clone(node.right);

        return copy;
    }
}
