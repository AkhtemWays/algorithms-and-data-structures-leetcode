package trees.ConstructBinaryTreefromPreorderandInorderTraversal;

import linkedlist.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Main {
    int preorderIndex = 0;
    Map<Integer, Integer> inorderIndices = new HashMap<>();
    public static void main(String[] args) {

    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) inorderIndices.put(inorder[i], i);

        return build( 0, inorder.length-1, preorder);
    }

    private TreeNode build(int left, int right, int[] preorder) {
        if (left > right) return null;
        TreeNode root = new TreeNode(preorder[preorderIndex++]);
        int rootIndex = inorderIndices.get(root.val);
        root.left = build(left, rootIndex-1, preorder);
        root.right = build(rootIndex+1, right, preorder);
        return root;
    }
}

