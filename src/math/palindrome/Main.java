package math.palindrome;

public class Main {
    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(1221));
        System.out.println(isPalindrome(1212));
    }

    public static boolean isPalindrome(int x) {
        String stringified = String.valueOf(x);
        for (int i = 0; i < stringified.length() / 2; i++) {
            if (stringified.charAt(i) != stringified.charAt(stringified.length() - 1 - i)) return false;
        }
        return true;
    }
}
