package arrays.WaystoSplitArrayIntoGoodSubarrays;

public class Main {
    private static void test1() {
        int[] nums = {0,1,1,1,0,0,1,1,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,0,1,1,0,0,1,0,0,0,0,0,0,0,1,1,1,1,0,0,1,1,1,1,1,1,0,0,1,1,1,0,1,0,1,0,1,1,0,0,0,0,0,1,0,0,1,0,0,1,1,1,1,1,0,0,0,0,0,1,1,0,0,0,0,0,1,1,0,0,0,0,1,0,1,0,0,0,1,0,0,1,1,0,1,1,1,1,1,0,1,1,0,0,1,1,1,0,0,1,0,0,1,1,0,1,0,1,1,1,0,0,1,0,0,0,1,0,0,1,0,0,1,1,1,1,0,0,0,0,0,1,0,1,0,0,1,0,1,0,1,0,1,1,1,0,0,0,1,0,1,1,1,1,1,0,1,0,1,0,0,0,0,1,0,0,0,1,1,1,0,1,1,0,1,1,1,0,1,1,0,1,1,0,0,0,1,0,1,1,0,0,1,1,1,0,1,1,1,0,1,0,1,1,0,0,1,1,1,0,0,1,1,1,1,0,0,1,0,0,1,0,1,0,1,1,1,0,1,0,0,0,0,1,1,0,0,0,0,0,1,0,1,0,1,1,1,0,0,1,0,1,0,1,0,1,0,1,1,0,0,1,0,0,1,0,1,1,0,0,0,0,1,0,0,0,1,0,1,0,1,1,1,1,0,0,0,1,0,0,1,0,0,0,1,0,1,1,1,0,1,1,0};
        System.out.println(numberOfGoodSubarraySplits(nums));
    }
    private static void test2() {
        int[] nums = {1,0,0,0,0,0,1,0,1};
        System.out.println(numberOfGoodSubarraySplits(nums));
    }
    public static void main(String[] args) {
        test1(); // 230630552
        test2(); // 12
    }

    public static int numberOfGoodSubarraySplits(int[] nums) {
        int k = -1;
        final int MOD = 100_000_000_7;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                k = i;
                break;
            }
        }
        if (k == -1) return 0;

        int answer = 1;
        for (int i = k+1; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (answer * (i - k) < 0) {
                    int half = (answer * ((i - k) / 2)) % MOD;
                    answer = (answer + half) % MOD;
                    if (i - k % 2 == 0)
                    answer = (answer + half) % MOD;
                } else {
                    answer = (answer * (i - k)) % MOD;
                }
                k = i;
            }
        }
        return answer;
    }
}
