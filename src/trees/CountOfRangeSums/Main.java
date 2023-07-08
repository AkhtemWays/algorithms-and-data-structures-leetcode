package trees.CountOfRangeSums;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

class SegmentTree {
    private final int min;
    private final int max;
    private final int[] tree;

    /**
     * Assume both min and max are non-negative here.
     **/
    public SegmentTree(int min, int max) {
        this.min = min;
        this.max = max;
        this.tree = new int[(max - min) * 4];
    }

    public int query(int min, int max) {
        return query(0, this.min, this.max, min, max);
    }

    /**
     * @param lo, the lower bound at the current tree index
     * @param hi, the higher bound at the current tree index
     * @param min, the lower bound in the query
     * @param max, the higher bound in the query
     **/
    private int query(int treeIndex, int lo, int hi, int min, int max) {
        if (min > hi || max < lo) {
            return 0;
        } else if (min <= lo && max >= hi) {
            return tree[treeIndex];
        }

        int mid = lo + (hi - lo) / 2;
        if (max <= mid) {
            return query(treeIndex * 2 + 1, lo, mid, min, max);
        } else if (min > mid) {
            return query(treeIndex * 2 + 2, mid + 1, hi, min, max);
        }

        int left = query(treeIndex * 2 + 1, lo, mid, min, mid);
        int right = query(treeIndex * 2 + 2, mid + 1, hi, mid + 1, max);

        return left + right;
    }

    public void update(int num) {
        update(0, this.min, this.max, num);
    }

    /**
     * @param lo, the lower bound at the current tree index
     * @param hi, the higher bound at the current tree index
     * @param num, the value to be added
     **/
    private void update(int treeIndex, int lo, int hi, int num) {
        if (lo == hi) {
            tree[treeIndex] += 1;
            return;
        }

        int mid = lo + (hi - lo) / 2;
        if (num <= mid) {
            update(treeIndex * 2 + 1, lo, mid, num);
        } else {
            update(treeIndex * 2 + 2, mid + 1, hi, num);
        }

        tree[treeIndex] = tree[treeIndex * 2 + 1] + tree[treeIndex * 2 + 2];
    }
}

public class Main {
    public static void main(String[] args) {
        int[] simple = {-2,5,-1};
        int[] harder = {-2, 1, 5, 4, -3, -6, 8, 2, 1};
        Main main = new Main();
        System.out.println(main.countRangeSum(simple, -2, 2));
        System.out.println(main.countRangeSum(harder, -2, 2));
    }
    public int countRangeSum(int[] nums, int lower, int upper) {
        // 1. calculate prefix sums
        // the reason we need an extra space, because the range sum from 0 to i,
        // can be seen as prefix sum at index i minus 0
        long[] prefixSums = new long[nums.length + 1];
        prefixSums[0] = 0;
        for (int index = 0; index < nums.length; index++) {
            if (index == 0) {
                prefixSums[index + 1] = nums[index];
            } else {
                prefixSums[index + 1] = prefixSums[index] + nums[index];
            }
        }

        TreeMap<Long, Integer> ranks = new TreeMap<>();
        int rank = 1;
        for (int i = 0; i < prefixSums.length; i++) {
            if (!ranks.containsKey(prefixSums[i])) {
                ranks.put(prefixSums[i], rank);
                rank++;
            }
        }

        SegmentTree st = new SegmentTree(0, rank);
        int answer = 0;

        for (int i = prefixSums.length-1; i >= 0; i--) {
            int minRank = -1;
            Map.Entry<Long, Integer> lowerBound = ranks.floorEntry(prefixSums[i] + lower);
            if (lowerBound != null) minRank = lowerBound.getValue();

            int maxRank = -1;
            Map.Entry<Long, Integer> upperBound = ranks.floorEntry(prefixSums[i] + upper);
            if (upperBound != null) maxRank = upperBound.getValue();

            int sum = st.query(minRank, maxRank);
            answer += sum;
            st.update(ranks.get(prefixSums[i]));
        }

        return answer;
    }
}
