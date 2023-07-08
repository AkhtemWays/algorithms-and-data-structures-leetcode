package strings.ReverseWordsinaStringIII;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }

    public static String reverseWords(String s) {
        StringBuilder res = Arrays.stream(s.split(" ")).map(str -> new StringBuilder(str).reverse()).reduce(new StringBuilder(), (a, b) -> a.append(b).append(' '));
        return res.substring(0 ,res.length()-1);
    }
}
