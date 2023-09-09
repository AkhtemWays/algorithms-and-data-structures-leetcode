package dynamicProgramming.MinimumOperationstoMakeaSpecialNumber;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println(minimumOperations("2245047")); // 2
        System.out.println(minimumOperations("2908305")); // 3
        System.out.println(minimumOperations("444937979672347396583725074143")); // 5
    }

    public static int minimumOperations(String num) {
        int base = num.contains("0") ? num.length()-1 : num.length();
        return Math.min(Math.min(try25(num), try00(num)), base);
    }

    private static int try25(String num) {
        int index5 = find('5', num);
        if (index5 == -1) return num.length();

        String rest = num.substring(0, index5);

        int index2 = find('2', rest);
        int index7 = find('7', rest);

        int idx = Math.max(index2, index7);


        return idx == -1 ? num.length() : num.length() - idx - 2;
    }

    private static int try00(String num) {
        int index0 = find('0', num);
        if (index0 == -1) return num.length();

        String rest = num.substring(0, index0);

        int index00 = find('0', rest);
        int index50 = find('5', rest);

        int idx = Math.max(index00, index50);

        if (idx == -1 || (idx == 0 && num.charAt(idx) == '0')) return num.length();

        return num.length() - idx - 2;
    }

    private static int find(char ch, String str) {
        int idx = str.lastIndexOf(ch);
        return idx;
    }
}
