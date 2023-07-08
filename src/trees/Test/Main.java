package trees.Test;

import linkedlist.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> roots = new ArrayList<>();
        roots.add(root);
        for (int nodeToDelete : to_delete) {
            roots = deleteNode(roots, nodeToDelete);
        }
        return roots;
    }

    private List<TreeNode> deleteNode(List<TreeNode> roots, int nodeToDelete) {
        TreeNode node = null, parent = null;
        for (TreeNode root : roots) {
            TreeNode[] meta = dfs(root, null, nodeToDelete);
            if (meta != null) {
                node = meta[0];
                parent = meta[1];
                break;
            }
        }

        if (node == null && parent == null) return roots;

        if (node.left != null) {
            roots.add(node.left);
        }
        if (node.right != null) {
            roots.add(node.right);
        }

        if (parent == null) {
            roots.remove(node);
        } else {
            if (parent.right == node) parent.right = null;
            else parent.left = null;
        }
        return roots;
    }

    private TreeNode[] dfs(TreeNode node, TreeNode parent, int nodeToDelete) {
        if (node == null) return null;
        if (node.val == nodeToDelete) return new TreeNode[]{node, parent};

        TreeNode[] leftSubTree = dfs(node.left, node, nodeToDelete);
        TreeNode[] rightSubTree = dfs(node.right, node, nodeToDelete);
        if (leftSubTree == null && rightSubTree == null) return null;
        return leftSubTree == null ? rightSubTree : leftSubTree;
    }
}
