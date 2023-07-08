package trees.segmentTree.SegmentTree;

public class SegmentTreeRecursive {
    int[] treeSum;
    int[] treeMax;
    int n;
    SegmentTreeRecursive(int[] arr) {
        this.n = arr.length;
        treeSum = new int[this.n * 4];
        treeMax = new int[this.n * 4];
        build(arr, 0, 0,arr.length-1);
    }

    private void build(int[] arr, int treeIndex, int left, int right) {
        if (left == right) {
            treeSum[treeIndex] = arr[left];
            treeMax[treeIndex] = arr[left];
            return;
        }
        int mid = (right + left) / 2;
        build(arr, leftChild(treeIndex), left, mid);
        build(arr, rightChild(treeIndex), mid+1, right);
        treeSum[treeIndex] = treeSum[leftChild(treeIndex)] + treeSum[rightChild(treeIndex)];
        treeMax[treeIndex] = Math.max(treeMax[leftChild(treeIndex)], treeMax[rightChild(treeIndex)]);
    }
    private static int leftChild(int index) {
        return 2 * index + 1;
    }
    private static int rightChild(int index) {
        return leftChild(index) + 1;
    }

    public int querySum(int left, int right) {
        return querySum(0, 0, this.n-1, left, right);
    }

    public int queryMax(int left, int right) {
        return queryMax(0, 0, n-1, left, right);
    }

    private int queryMax(int treeIndex, int l, int r, int left, int right) {
        if (right < l || r < left) return -1;
        if (l == r) return treeMax[treeIndex];

        int mid = (r + l) / 2;
        return Math.max(queryMax(leftChild(treeIndex), l, mid, left, right), queryMax(rightChild(treeIndex), mid+1, r, left, right));
    }

    private int querySum(int treeIndex, int l, int r, int left, int right) {
        if (right < l || l > r) return 0;
        if (left <= l && r <= right) return treeSum[treeIndex];
        int mid = (r + l) / 2;
        return querySum(leftChild(treeIndex), l, mid, left, right) + querySum(rightChild(treeIndex), mid+1, r, left, right);
    }

    private void update(int treeIndex, int l, int r, int pos, int val) {
        if (l == r) {
            treeSum[treeIndex] = val;
            treeMax[treeIndex] = val;
            return;
        }
        int mid = (r + l) / 2;
        if (pos <= mid) {
            update(leftChild(treeIndex), l, mid, pos, val);
        } else {
            update(rightChild(treeIndex), mid+1, r, pos, val);
        }
        treeSum[treeIndex] = treeSum[leftChild(treeIndex)] + treeSum[rightChild(treeIndex)];
        treeMax[treeIndex] = Math.max(treeMax[leftChild(treeIndex)], treeMax[rightChild(treeIndex)]);
    }

    public void update(int pos, int val) {
        update(0, 0, n-1, pos, val);
    }
}
