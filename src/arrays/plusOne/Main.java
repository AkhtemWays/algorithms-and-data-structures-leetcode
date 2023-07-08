package arrays.plusOne;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] digits = {8, 9, 9, 9, 9};
        int[] result = plusOne(digits);
        System.out.println(Arrays.toString(result));
    }

    public static int[] plusOne(int[] digits) {
        int j = digits.length - 1;
        if (digits[0] != 9 && digits.length == 1) {
            digits[0] += 1;
            return digits;
        } else if (digits[0] == 9 && digits.length == 1) {
            int[] newArray = new int[2];
            newArray[0] = 1;
            return newArray;
        }
        while (j >= 0) {
            if (digits[j] == 9) {
                digits[j] = 0;
                j--;
            } else {
                digits[j] += 1;
                return digits;
            }
        }
        if (j == -1) {
            int[] newArray = new int[digits.length + 1];
            newArray[0] = 1;
            for (int i = 1; i < digits.length + 1; i++) {
                newArray[i] = digits[i - 1];
            }
            return newArray;
        }
        return digits;
    }
}
