package strings.StringCompression;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        char[] chars = {'a','a','b','b','c','c','c'}; // 6
        char[] chars2 = {'a','b'}; // 2
        char[] chars3 = {'a','b','b','b','b','b','b','b','b','b','b','b','b'}; // 4
        char[] chars4 = {'a','a','a','b','b','a','a'}; // 6
        char[] chars5 = {'a','a','a','a','a','a','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','c','c','c','c','c','c','c','c','c','c','c','c','c','c'}; // 8
        char[] chars6 = {'G','t','W','Y','v','&',':','d','#','k'}; // 10
        char[] chars7 = {'7','7','7','a','a','b',':',':',':',':'}; // 7
        System.out.println(compress(chars));
        System.out.println(compress(chars2));
        System.out.println(compress(chars3));
        System.out.println(compress(chars4));
        System.out.println(compress(chars5));
        System.out.println(compress(chars6));
        System.out.println(compress(chars7));
    }

    public static int compress(char[] chars) {

        int i = 0, res = 0;
        while (i < chars.length) {
            int groupLength = 1;
            while (i + groupLength < chars.length && chars[i + groupLength] == chars[i]) {
                groupLength++;
            }
            chars[res++] = chars[i];
            if (groupLength > 1) {
                for (char ch : Integer.toString(groupLength).toCharArray()) {
                    chars[res++] = ch;
                }
            }
            i += groupLength;
        }
        return res;
    }
}
