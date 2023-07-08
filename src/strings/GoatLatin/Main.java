package strings.GoatLatin;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }

    public static String toGoatLatin(String sentence) {
        String vowels = "aeiou";
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (vowels.contains(String.valueOf(word.charAt(0)).toLowerCase())) {
                sb.append(word).append("ma").append("a".repeat(i+1));
            } else {
                sb.append(word.substring(1)).append(word.charAt(0)).append("ma").append("a".repeat(i+1));
            }
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
