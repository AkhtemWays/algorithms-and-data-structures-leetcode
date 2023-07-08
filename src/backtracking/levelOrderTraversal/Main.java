package backtracking.levelOrderTraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (q.size() > 0) {
            List<Integer> cur = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode parent = q.remove();
                cur.add(parent.val);
                if (parent.left != null) q.add(parent.left);
                if (parent.right != null) q.add(parent.right);
            }
            result.add(cur);
        }
        return result;
    }
}
