package strings.DecodeString;

import java.util.Stack;

public class RecursionApproach {
    public static void main(String[] args) {
        System.out.println(decodeString("3[a]2[bc]")); // aaabcbc
        System.out.println(decodeString("2[abc]3[cd]ef")); // abcabccdcdcdef
        System.out.println(decodeString("3[a2[c]]")); // accaccacc
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) sb.append("leetcode");
        System.out.println(decodeString("100[leetcode]").equals(sb.toString()));
    }

    public static String decodeString(String s) {
        StringBuilder repetitions = new StringBuilder();
        StringBuilder result = new StringBuilder();
        int i = -1;
        while (++i < s.length()) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                repetitions.append(ch);
            }
            else if (Character.isLetter(ch)) {
                result.append(ch);
            } else if (ch == '[') {
                CodeWithIndex codeWithIndex = getInnerCode(s, ++i);
                i = codeWithIndex.i;
                String decodedInnerCode = decodeString(codeWithIndex.code);
                for (int k = 0; k < Integer.parseInt(repetitions.toString()); k++) result.append(decodedInnerCode);
                repetitions = new StringBuilder();
            }
        }
        return result.toString();
    }

    private static CodeWithIndex getInnerCode(String s, int i) {
        StringBuilder innerCode = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        stack.add('[');
        while (!stack.isEmpty()) {
            innerCode.append(s.charAt(i));
            i++;
            if (s.charAt(i) == '[') stack.add('[');
            else if (s.charAt(i) == ']') stack.pop();
        }
        return new CodeWithIndex(innerCode.toString(), i);
    }

    private static class CodeWithIndex {
        String code;
        int i;
        CodeWithIndex(String code, int i) {
            this.code = code;
            this.i = i;
        }
    }
}
