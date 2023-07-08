package strings.RemoveDuplicateLetters;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.removeDuplicateLetters("bcabc"));
        System.out.println(main.removeDuplicateLetters("cbacdcbc"));
    }

    public String removeDuplicateLetters(String s) {
        Set<Character> visited = new HashSet<>();
        List<String> chars = new ArrayList<>();
        for (char ch : s.toCharArray()) {
            if (!visited.contains(ch)) {
                visited.add(ch);
                chars.add(String.valueOf(ch));
            }
        }
        Collections.sort(chars);
        return String.join("", chars);
    }
}
