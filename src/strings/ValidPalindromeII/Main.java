package strings.ValidPalindromeII;

public class Main {
    public static void main(String[] args) {
        System.out.println(validPalindrome("aba")); // true
        System.out.println(validPalindrome("abca")); // true
        System.out.println(validPalindrome("abc")); // false;
        System.out.println(validPalindrome("tebbem")); // false
    }

    public static boolean validPalindrome(String s) {
        for (int i = 0; i < s.length()/2; i++) {
            if (s.charAt(i) != s.charAt(s.length()-1-i)) {
                return isPalindrome(s.substring(i+1, s.length()-i)) || isPalindrome(s.substring(i, s.length()-1-i));
            }
        }
        return true;
    }

    private static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length()-1-i)) return false;
        }
        return true;
    }
}
