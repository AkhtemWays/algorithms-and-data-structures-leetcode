package trees.CounttheNumberofKBigIndices;

class FenwickTree {
    int[] BIT;
    int n;

    FenwickTree(int[] buckets) {
        n = buckets.length;
        BIT = new int[n+1];
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

    public int sum(int i) {
        i++;
        int sum = 0;
        while (i > 0) {
            sum += BIT[i];
            i -= i & (-i);
        }
        return sum;
    }

    public int sum(int l, int r) {
        return sum(r) - sum(l-1);
    }
}

public class Main {
    public static void main(String[] args) {
        int[] nums = {2,3,6,5,2,3};
        System.out.println(kBigIndices(nums, 2));
    }

    public static int kBigIndices(int[] nums, int k) {
        int n = 100001;
        int[] buckets = new int[100001];
        for (int num : nums) {
            buckets[num]++;
        }

        FenwickTree left = new FenwickTree(new int[n]);
        FenwickTree right = new FenwickTree(buckets);
        right.update(-1, nums[0]);
        left.update(1, nums[0]);
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            right.update(-1, nums[i]);
            int leftCount = left.sum(1, nums[i]-1);
            int rightCount = right.sum(1, nums[i]-1);
            if (leftCount >= k && rightCount >= k) count++;
            left.update(1, nums[i]);
        }
        return count;
    }
}
