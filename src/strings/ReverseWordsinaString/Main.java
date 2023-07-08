package strings.ReverseWordsinaString;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }

    public String reverseWords(String s) {
        String[] words = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length-1; i >= 0; i--) {
            sb.append(words[i]).append(' ');
        }
       return sb.deleteCharAt(sb.length()-1).toString();
    }
}
