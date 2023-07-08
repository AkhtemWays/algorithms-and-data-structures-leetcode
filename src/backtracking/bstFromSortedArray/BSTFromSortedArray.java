package backtracking.bstFromSortedArray;

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

public class BSTFromSortedArray {
    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};
        TreeNode root = sortedArrayToBST(nums);
        System.out.println("root value: " + root.val);
        System.out.println("left child value: " + root.left.val);
        System.out.println("right child value: " + root.right.val);
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;
        int mid = (nums.length - 1) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = constructTree(nums, 0, mid - 1);
        root.right = constructTree(nums, mid + 1, nums.length - 1);
        return root;
    }

    public static TreeNode constructTree(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = constructTree(nums, left, mid - 1);
        node.right = constructTree(nums, mid + 1, right);
        return node;
    }
}
