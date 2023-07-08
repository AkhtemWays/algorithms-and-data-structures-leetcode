package arrays.RunningSumof1dArray;

public class Main {
    public static void main(String[] args) {

    }

    public int[] runningSum(int[] nums) {
        int sum = 0;
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = sum += nums[i];
        }
        return res;
    }
}
