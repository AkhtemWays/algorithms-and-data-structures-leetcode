package greedy.MinimumSumofFourDigitNumberAfterSplittingDigits;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.minimumSum(2932));
    }

    public int minimumSum(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        Arrays.sort(chars);
        String num1 = chars[0] + String.valueOf(chars[2]);
        String num2 = String.valueOf(chars[1]) + chars[3];
        return Integer.parseInt(num1) + Integer.parseInt(num2);
    }
}
