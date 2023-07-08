package math.integerToRoman;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

    }

    public static String intToRoman(int num) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < num / 1000; i++) res.append("M");
        num = num % 1000;

        // hundred
        if (num >= 900)  {
            res.append("CM");
            num -= 900;
        }
        else if (num >= 400 && num < 500) {
            res.append("CD");
            num -= 400;
        }
        else if (num >= 500) {
            res.append("D");
            num -= 500;
            while (num >= 100) {
                num -= 100;
                res.append("C");
            }
        }
        else {
            while (num >= 100) {
                num -= 100;
                res.append("C");
            }
        }

        // tens
        if (num >= 90)  {
            res.append("XC");
            num -= 90;
        }
        else if (num >= 40 && num < 50) {
            res.append("XL");
            num -= 40;
        }
        else if (num >= 50) {
            res.append("L");
            num -= 50;
            while (num >= 10) {
                num -= 10;
                res.append("X");
            }
        }
        else {
            while (num >= 10) {
                num -= 10;
                res.append("X");
            }
        }

        // ones
        if (num == 9)  {
            res.append("IX");
            num -= 9;
        }
        else if (num == 4) {
            res.append("IV");
            num -= 4;
        }
        else if (num >= 5) {
            res.append("V");
            num -= 5;
            while (num >= 1) {
                num -= 1;
                res.append("I");
            }
        }
        else {
            while (num >= 1) {
                num -= 1;
                res.append("I");
            }
        }

        return res.toString();
    }

    static Map<Character, Integer> getRomanToInteger() {
        Map<Character, Integer> romanToInteger = new HashMap<>();
        romanToInteger.put('I', 1);
        romanToInteger.put('V', 5);
        romanToInteger.put('X', 10);
        romanToInteger.put('L', 50);
        romanToInteger.put('C', 100);
        romanToInteger.put('D', 500);
        romanToInteger.put('M', 1000);
        return romanToInteger;
    }
}
