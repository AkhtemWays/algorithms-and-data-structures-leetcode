package trie.wordDictionary;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class WordDictionary {
    Set<String> dict = new HashSet<>();
    public static void main(String[] args) {

    }

    public WordDictionary() {

    }

    public void addWord(String word) {
        dict.add(word);
    }

    public boolean search(String word) {
        Set<String> words = dict.stream().filter(w -> w.length() >= word.length()).collect(Collectors.toSet());
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch == '.') continue;
            int finalI = i;
            words = words.stream().filter(w -> w.charAt(finalI) == ch).collect(Collectors.toSet());
            if (words.isEmpty()) return false;
        }
        return true;
    }
}
