package trees.CountofSmallerNumbersAfterSelf;


import java.util.ArrayList;
import java.util.List;

class FenwickTree {
    private final int[] BIT;
    private final int n;
    public FenwickTree(int[] buckets) {
        n = buckets.length;
        BIT = new int[n+1];
        build(buckets);
    }

    private void build(int[] buckets) {
        for (int i = 0; i < n; i++) {
            update(buckets[i], i);
        }
    }

    public void update(int val, int i) {
        i++;
        while (i <= n) {
            BIT[i] += val;
            i += i & (-i);
        }
    }

    public int sum(int l, int r) {
        return sum(r) - sum(l-1);
    }

    public int sum(int i) {
        i++;
        int sum = 0;
        while (i > 0) {
            sum += BIT[i];
            i -= i & (-i);
        }
        return sum;
    }
}

public class FenwickTreeApproach {
    public static void main(String[] args) {
        int[] nums = {5,2,6,1}; // 2,1,1,0
        int[] nums2 = {5,4,5,2,6,5,6,1}; // [3, 2, 2, 1, 2, 1, 1, 0]
        System.out.println(countSmaller(nums));
        System.out.println(countSmaller(nums2));
    }

    private static final int offset = 10001;
    public static List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] buckets = new int[offset * 2];
        for (int num : nums) {
            buckets[num + offset]++;
        }

        FenwickTree ft = new FenwickTree(buckets);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int smaller = ft.sum(nums[i]-1 + offset);
            res.add(smaller);
            ft.update(-1, nums[i] + offset);
        }
        return res;
    }
}
