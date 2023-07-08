package trees.simpleBinaryTree.PathSumIII;

import java.util.HashMap;

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
    HashMap<Integer, Integer> prefixSums = new HashMap<>();
    int k;
    int count = 0;
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);
        Main main = new Main();
        System.out.println(main.pathSum(root, 8));
    }

    public int pathSum(TreeNode root, int sum) {
        k = sum;
        prefixSums.put(0, 1);
        preOrder(root, 0);
        return count;
    }

    private void preOrder(TreeNode node, int curSum) {
        if (node == null) return;

        curSum += node.val;
        if (curSum == k)
            count++;
        count += prefixSums.getOrDefault(curSum - k, 0);

        prefixSums.put(curSum, prefixSums.getOrDefault(curSum, 0) + 1);
        preOrder(node.left, curSum);
        preOrder(node.right, curSum);
        prefixSums.put(curSum, prefixSums.get(curSum) - 1);
    }
}
