package trees.ConstructBinaryTreefromInorderandPostorderTraversal;

import linkedlist.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Main {
    int postorderIndex = 0;
    Map<Integer, Integer> inorderIndices = new HashMap<>();
    public static void main(String[] args) {

    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) inorderIndices.put(inorder[i], i);
        postorderIndex = postorder.length-1;
        return build(0, inorder.length-1, postorder);
    }

    private TreeNode build(int left, int right, int[] postorder) {
        if (left > right) return null;

        TreeNode root = new TreeNode(postorder[postorderIndex--]);
        root.right = build(inorderIndices.get(root.val)+1, right, postorder);
        root.left = build(left, inorderIndices.get(root.val)-1, postorder);
        return root;
    }
}
