package strings.BreakaPalindrome;

public class Main {
    public static void main(String[] args) {
        System.out.println(breakPalindrome("abccba")); // aaccba
        System.out.println(breakPalindrome("a")); // ""
        System.out.println(breakPalindrome("aa")); // ab
        System.out.println(breakPalindrome("aba")); // ab
    }

    public static String breakPalindrome(String palindrome) {
        if (palindrome.length() == 1) return "";
        int i = -1;
        int mid = palindrome.length() / 2;
        boolean isEven = mid * 2 == palindrome.length();
        while (++i < mid) {
            if (palindrome.charAt(i) != 'a') {
                break;
            }
        }
        if (i == mid && !isEven) {
            return palindrome.substring(0, i+1) + (palindrome.charAt(i+1) == 'a' ? 'b' : 'a') + palindrome.substring(i+2);
        }
        return palindrome.substring(0, i) + (i == mid ? 'b' : 'a') + palindrome.substring(i+1);
    }
}
