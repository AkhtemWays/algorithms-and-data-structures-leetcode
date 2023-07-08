package twoPointers.firstOccurenceOfString;

public class Main {
    public static void main(String[] args) {
        System.out.println(strStr("mississippi", "pi"));
    }

    public static int strStr(String haystack, String needle) {
        int j = haystack.indexOf(needle);
        if (needle.length() > haystack.length()) return -1;
        int slow = 0;
        while (slow < haystack.length()) {
            int i = 0;
            if (haystack.charAt(slow) == needle.charAt(i)) {
                int fast = slow;
                while (fast < haystack.length() && haystack.charAt(fast++) == needle.charAt(i++)) {
                    if (i == needle.length()) return slow;
                }
            }
            slow++;
        }
        return -1;
    }
}
