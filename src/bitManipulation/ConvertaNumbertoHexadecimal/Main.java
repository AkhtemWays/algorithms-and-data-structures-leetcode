package bitManipulation.ConvertaNumbertoHexadecimal;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println(toHex(0));
        System.out.println(toHex(16));
        System.out.println(toHex(26));
        System.out.println(toHex(-1));
        System.out.println(toHex(Integer.MAX_VALUE));
        System.out.println(toHex(Integer.MIN_VALUE));
    }

    private static final char[] m = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final HashMap<Character, Integer> rm = reverseMap();
    public static String toHex(int num) {
        if (num == 0) return "0";
        num = Math.abs(num);

        StringBuilder res = new StringBuilder();
        for (int i = 28; i >= 0; i -= 4) {
            if (num >= 1 << i || res.length() > 0) {
                res.append(m[num / (1 << i)]);
                num %= 1 << i;
            }
        }
        return res.toString();
    }

    private static HashMap<Character, Integer> reverseMap() {
        HashMap<Character, Integer> m = new HashMap<>();
        m.put('0', 0);
        m.put('1', 1);
        m.put('2', 2);
        m.put('3', 3);
        m.put('4', 4);
        m.put('5', 5);
        m.put('6', 6);
        m.put('7', 7);
        m.put('8', 8);
        m.put('9', 9);
        m.put('a', 10);
        m.put('b', 11);
        m.put('c', 12);
        m.put('d', 13);
        m.put('e', 14);
        m.put('f', 15);
        return m;
    }
}
