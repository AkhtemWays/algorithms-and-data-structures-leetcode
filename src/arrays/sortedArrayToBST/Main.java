package arrays.sortedArrayToBST;

import java.util.List;

class Node {
    int val;
    Node left;
    Node right;
    Node() {}
    Node(int val) { this.val = val; }
    Node(int val, Node left, Node right) {
        this.val = val;
         this.left = left;
         this.right = right;
    }
}


public class Main {

    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};
        Node root = sortedArrayToBST(nums);
        List<Integer> tree = new java.util.ArrayList<>(List.of(root.val));
        Node leftChild = root.left;
        Node rightChild = root.right;
        while (leftChild != null || rightChild != null) {
            if (leftChild != null) {
                tree.add(leftChild.val);
                leftChild = leftChild.left;
            }
            if (rightChild != null) {
                tree.add(rightChild.val);
                rightChild = rightChild.right;
            }
        }
        tree.forEach(System.out::println);
    }

    public static Node sortedArrayToBST(int[] nums) {
        if (nums.length == 1) return new Node(nums[0]);
        int mid = nums.length / 2;
        Node root = new Node(nums[mid]);
        findChildren(nums, mid - 1, true, root);
        findChildren(nums, mid + 1, false, root);

        return root;
    }

    public static void findChildren(int[] nums, int index, boolean isLeftDirection, Node node) {
        if (isLeftDirection && index >= 0) {
            node.left = new Node(nums[index]);
            findChildren(nums, index - 1, true, node.left);
        } if (!isLeftDirection && index <= nums.length - 1) {
            node.right = new Node(nums[index]);
            findChildren(nums, index + 1, false, node.right);
        }
    }
}
