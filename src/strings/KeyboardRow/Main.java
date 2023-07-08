package strings.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private final String row1 = "qwertyuiop";
    private final String row2 = "asdfghjkl";
    private final String row3 = "zxcvbnm";

    public static void main(String[] args) {
        Main main = new Main();
        String[] words1 = {"Hello","Alaska","Dad","Peace"};
        System.out.println(Arrays.toString(main.findWords(words1)));
        String[] words2 = {"omk"};
        System.out.println(Arrays.toString(main.findWords(words2)));
        String[] words3 = {"adsdf","sfd"};
        System.out.println(Arrays.toString(main.findWords(words3)));
    }

    public String[] findWords(String[] words) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            String s = String.valueOf(word.charAt(0)).toLowerCase();
            if (row1.contains(s)) {
                boolean allContain = true;
                for (char ch : word.toLowerCase().toCharArray()) {
                    if (!row1.contains(String.valueOf(ch))) {
                        allContain = false;
                        break;
                    }
                }
                if (allContain) res.add(word);
            }
            else if (row2.contains(s)) {
                boolean allContain = true;
                for (char ch : word.toLowerCase().toCharArray()) {
                    if (!row2.contains(String.valueOf(ch))) {
                        allContain = false;
                        break;
                    }
                }
                if (allContain) res.add(word);
            }
            else {
                boolean allContain = true;
                for (char ch : word.toLowerCase().toCharArray()) {
                    if (!row3.contains(String.valueOf(ch))) {
                        allContain = false;
                        break;
                    }
                }
                if (allContain) res.add(word);
            }
        }
        String[] r = new String[res.size()];
        for (int i = 0; i < res.size(); i++) r[i] = res.get(i);
        return r;
    }
}
