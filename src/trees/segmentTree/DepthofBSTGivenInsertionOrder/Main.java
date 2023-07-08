package trees.segmentTree.DepthofBSTGivenInsertionOrder;

import java.util.Arrays;
import java.util.HashMap;

class SegmentTree {
    int[] tree;
    int n;
    SegmentTree(int[] tree) {
        n = tree.length;
        this.tree = new int[4 * n];
        Arrays.fill(this.tree, Integer.MAX_VALUE);
        build(0, 0, n-1, tree);
    }

    private void build(int treeIndex, int left, int right, int[] input) {
        if (left == right) {
            tree[treeIndex] = input[left];
        } else {
            int mid = left + (right - left) / 2;
            build(leftChild(treeIndex), left, mid, input);
            build(rightChild(treeIndex), mid+1, right, input);
            tree[treeIndex] = Math.min(tree[leftChild(treeIndex)], tree[rightChild(treeIndex)]);
        }
    }

    public int getMin(int left, int right) {
        return getMin(0, 0, n-1, left, right);
    }

    private int getMin(int treeIndex, int l, int r, int left, int right) {
        if (right < l || r < left) return Integer.MAX_VALUE;
        if (left <= l && r <= right) return tree[treeIndex];

        int mid = (r + l) / 2;
        int leftChild = getMin(leftChild(treeIndex), l, mid, left, right);
        int rightChild = getMin(rightChild(treeIndex), mid+1, r, left, right);
        return Math.min(leftChild, rightChild);
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return leftChild(index) + 1;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] input = {4, 2, 6, 1, 10, -1, 12, 3, 8, 7};
        int[] input2 = {2,1,4,3};
        System.out.println(maxDepthBST(input));
        System.out.println(maxDepthBST(input2));
    }

    public static int maxDepthBST(int[] sortedOrder) {
        if (sortedOrder.length <= 2) return sortedOrder.length;
        HashMap<Integer, Integer> valueIndicesMap = new HashMap<>();
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < sortedOrder.length; i++) {
            valueIndicesMap.put(sortedOrder[i], i);
            indexMap.put(i, sortedOrder[i]);
        }

        int[] realOrder = Arrays.copyOf(sortedOrder, sortedOrder.length);
        Arrays.sort(sortedOrder);
        int[] sortedOrderIndices = new int[sortedOrder.length];
        HashMap<Integer, Integer> sortedOrderIndicesMap = new HashMap<>();
        for (int i = 0; i < sortedOrder.length; i++) {
            sortedOrderIndices[i] = valueIndicesMap.get(sortedOrder[i]);
            sortedOrderIndicesMap.put(sortedOrder[i], i);
        }
        SegmentTree tree = new SegmentTree(sortedOrderIndices);
        return 1 + getHeight(sortedOrderIndicesMap.get(realOrder[0]), tree, indexMap, 0, sortedOrder.length-1);
    }

    private static int getHeight(int mid, SegmentTree tree, HashMap<Integer, Integer> indexMap, int left, int right) {
        if (left >= right) {
            return 0;
        } else if (right - left == 1) return 1;
        int leftChild = Integer.MAX_VALUE;
        int rightChild = Integer.MAX_VALUE;
        if (mid - 1 - left > 0) {
            leftChild = tree.getMin(left, mid-1);
        }
        if (right - mid - 1 > 0) {
            rightChild = tree.getMin(mid+1, right);
        }
        int leftHeight = getHeight(indexMap.get(leftChild), tree, indexMap, left, mid-1);
        int rightHeight = getHeight(indexMap.get(rightChild), tree, indexMap, mid+1, right);
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
