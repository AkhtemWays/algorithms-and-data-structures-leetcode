package arrays.NumberofBeautifulPairs;

public class Main {
    private static void test1() {
        int[] nums = {2,5,1,4};
        System.out.println(countBeautifulPairs(nums));
    }
    private static void test2() {
        int[] nums = {11,21,12};
        System.out.println(countBeautifulPairs(nums));
    }
    public static void main(String[] args) {
        test1();
        test2();
    }

    public static int countBeautifulPairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                String num1 = String.valueOf(nums[i]);
                String num2 = String.valueOf(nums[j]);
                int n1 = Character.getNumericValue(num1.charAt(0));
                int n2 = Character.getNumericValue(num2.charAt(num2.length()-1));
                if (gcd(n1, n2) == 1) count++;
            }
        }
        return count;
    }

    private static int gcd(int num1, int num2) {
        while (Math.min(num1, num2) > 0) {
            if (num1 > num2) {
                num1 %= num2;
            } else {
                num2 %= num1;
            }
        }
        return Math.max(num1, num2);
    }
}
