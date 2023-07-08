package arrays.MaxConsecutiveOnes;

public class Main {
    public static void main(String[] args) {

    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        for (int i = 0, count = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count = 0;
            } else {
                count++;
                res = Math.max(count, res);
            }
        }
        return res;
    }
}
