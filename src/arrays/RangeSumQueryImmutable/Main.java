package arrays.RangeSumQueryImmutable;

import java.util.HashMap;

class NumArray {

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(2, 5));
    }
    private final HashMap<String, Integer> cache = new HashMap<>();
    private final int[] nums;

    public NumArray(int[] nums) {
        this.nums = nums;
    }

    public int sumRange(int left, int right) {
        String key = left + "_" + right;
        if (cache.containsKey(key)) return cache.get(key);
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += nums[i];
        }
        cache.put(key, sum);
        return sum;
    }
}
