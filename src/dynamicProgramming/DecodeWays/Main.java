package dynamicProgramming.DecodeWays;

import java.util.*;

public class Main {
    private final Set<Character> chars = new HashSet<>(List.of('1', '2', '3', '4', '5', '6'));
    public static void main(String[] args) {
        Main main = new Main();
        String s = "126"; // 3
        String s2 = "1"; // 1
        String s3 = "10"; // 1
        String s4 = "106"; // 1
        String s5 = "1123"; // 5
        String s6 = "226"; // 3
        String s7 = "2611055971756562"; // 4
        System.out.println(main.numDecodings(s));
        System.out.println(main.numDecodings(s2));
        System.out.println(main.numDecodings(s3));
        System.out.println(main.numDecodings(s4));
        System.out.println(main.numDecodings(s5));
        System.out.println(main.numDecodings(s6));
        System.out.println(main.numDecodings(s7));
    }

    public int numDecodings(String s) {

        if (s.charAt(0) == '0') return 0;

        int result = 1;
        int prev = 1;
        int prevValue = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (s.charAt(i-1) == '1' || s.charAt(i-1) == '2') {
                    result = prevValue;
                } else {
                    return 0;
                }
            }
            else if ((chars.contains(s.charAt(i)) && s.charAt(i-1) == '2') || s.charAt(i-1) == '1') {
                result = prev + prevValue;
            }
            prevValue = prev;
            prev = result;
        }
        return result;
    }
}
