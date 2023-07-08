package trees.SmallestSubarraysWithMaximumBitwiseOR;

import java.util.Arrays;

class SegmentTree {
    private final int[] tree;
    private final int n;
    SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        build(0, 0, n-1, arr);
    }

    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    private static int rightChild(int i) {
        return leftChild(i) + 1;
    }

    private void build(int nodeIndex, int l, int r, int[] arr) {
        if (l == r) {
            tree[nodeIndex] = arr[l];
        } else {
            int mid = l + (r - l) / 2;
            build(leftChild(nodeIndex), l, mid, arr);
            build(rightChild(nodeIndex), mid+1, r, arr);
            tree[nodeIndex] = tree[leftChild(nodeIndex)] | tree[rightChild(nodeIndex)];
        }
    }

    public int[] query(int nodeIndex, int start, int end, int left, int right, int length) {
        if (right < start || left > end) {
            return new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        }
        if (left >= start && right <= end) {
            return new int[]{tree[nodeIndex], length};
        }
        int mid = start + (end - start) / 2;
        int[] leftSubtree = query(leftChild(nodeIndex), start, mid, left, right, length << 1);
        int[] rightSubtree = query(rightChild(nodeIndex), mid+1, end, left, right, length << 1);
        return new int[]{Math.max(leftSubtree[0], rightSubtree[0]), leftSubtree[1] + rightSubtree[1]};
    }
}

public class Main {
    public static void main(String[] args) {
        int[] nums = {1,0,2,1,3};
        System.out.println(Arrays.toString(smallestSubarrays(nums)));
    }

    public static int[] smallestSubarrays(int[] nums) {
        SegmentTree st = new SegmentTree(nums);
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = st.query(0, 0, n-1, i, n-1, 1)[1];
        }
        return res;
    }
}
