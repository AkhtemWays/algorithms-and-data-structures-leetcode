package bitManipulation.singleNumber;

public class Main {
    public static void main(String[] args) {
        System.out.println(16 | 16);
    }

    public static int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }
}
