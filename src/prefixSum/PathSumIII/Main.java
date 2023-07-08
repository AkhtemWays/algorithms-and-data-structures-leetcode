package prefixSum.PathSumIII;

import linkedlist.TreeNode;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);
        root.right.right = new TreeNode(11);
        System.out.println(main.pathSum(root, 8));
    }

    int count = 0;
    int k;
    HashMap<Long, Integer> h = new HashMap();

    public void preorder(TreeNode node, long currSum) {
        if (node == null)
            return;

        // current prefix sum
        currSum += node.val;

        // here is the sum we're looking for
        if (currSum == k)
            count++;

        // number of times the curr_sum − k has occured already,
        // determines the number of times a path with sum k
        // has occured upto the current node
        count += h.getOrDefault(currSum - k, 0);

        // add the current sum into hashmap
        // to use it during the child nodes processing
        h.put(currSum, h.getOrDefault(currSum, 0) + 1);

        // process left subtree
        preorder(node.left, currSum);
        // process right subtree
        preorder(node.right, currSum);

        if (h.get(currSum) == 1) {
            h.remove(currSum);
        } else {
            h.put(currSum, h.get(currSum) - 1);
        }
    }

    public int pathSum(TreeNode root, int sum) {
        k = sum;
        preorder(root, 0L);
        return count;
    }
}
