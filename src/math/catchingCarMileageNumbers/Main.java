package math.catchingCarMileageNumbers;

public class Main {
    public static void main(String[] args) {
        System.out.println(isInteresting(3, new int[]{1337, 256}) == 0);
        System.out.println(isInteresting(3236, new int[]{1337, 256}) == 0);
        System.out.println(isInteresting(98, new int[]{1337, 256}) == 1);

// progress as we near an "interesting" number
        System.out.println(isInteresting(11207, new int[]{}) == 0);
        System.out.println(isInteresting(11208, new int[]{}) == 0); // 0
        System.out.println(isInteresting(11209, new int[]{}) == 1); // 1
        System.out.println(isInteresting(11210, new int[]{}) == 1); // 1
        System.out.println(isInteresting(11211, new int[]{}) == 2); // 2

// nearing a provided "awesome phrase"
        System.out.println(isInteresting(1335, new int[]{1337, 256}) == 1); // 1
        System.out.println(isInteresting(1336, new int[]{1337, 256}) == 1); // 1
        System.out.println(isInteresting(1337, new int[]{1337, 256}) == 2); // 2
    }

    public static int isInteresting(int number, int[] awesomePhrases) {
        if (interesting(number, awesomePhrases)) return 2;
        else if (interesting(number + 1, awesomePhrases) || interesting(number + 2, awesomePhrases)) return 1;
        return 0;
    }

    public static boolean interesting(int number, int[] awesomePhrases) {
        for (int awesomePhrase : awesomePhrases)
            if (awesomePhrase == number) return true;
        if (String.valueOf(number).length() < 3) return false;
        if (isPalindrome(number)) return true;
        if (zeroFollowed(number)) return true;
        if (everyDigitSameNumber(number)) return true;
        if (isSequentiallyIncrementing(number)) return true;
        return isSequentiallyDecrementing(number);
    }

    public static boolean isPalindrome(int number) {
        String num = String.valueOf(number);
        for (int i = 0; i < num.length() / 2; i++) {
            if (num.charAt(i) != num.charAt(num.length() - 1 - i)) return false;
        }
        return true;
    }

    public static boolean zeroFollowed(int number) {
        String num = String.valueOf(number);
        for (int i = 1; i < num.length(); i++) {
            if (num.charAt(i) != '0') return false;
        }
        return true;
    }

    public static boolean everyDigitSameNumber(int number) {
        String num = String.valueOf(number);
        int i = 1;
        while (i < num.length() && num.charAt(i) == num.charAt(i-1)) i++;
        return i == num.length();
    }

    public static boolean isSequentiallyIncrementing(int number) {
        String num = String.valueOf(number);
        for (int i = 1; i < num.length(); i++) {
            int x = Character.getNumericValue(num.charAt(i));
            int y = Character.getNumericValue(num.charAt(i-1));
            if (x == 0 && y == 9) continue;
            if (x <= y || (x == 1 && y == 0)) return false;
        }
        return true;
    }

    public static boolean isSequentiallyDecrementing(int number) {
        String num = String.valueOf(number);
        for (int i = 1; i < num.length(); i++) {
            int x = Character.getNumericValue(num.charAt(i));
            int y = Character.getNumericValue(num.charAt(i-1));
            if (x == 9 && y == 0) return false;
            if (x >= y) return false;
        }
        return true;
    }
}
