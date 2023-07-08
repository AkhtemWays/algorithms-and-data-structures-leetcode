package trie.WordBreak;

import java.util.ArrayList;
import java.util.List;

class Node {
    char val;
    List<Node> children;
    boolean isEnd = false;
    public Node(char val) {
        this.val = val;
        children = new ArrayList<>();
    }
}

public class Main {
    public static void main(String[] args) {
        String s = "cars";
        String s2 = "leetcode";
        String s3 = "aaaaaaa";
        String s4 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        Main main = new Main();
        System.out.println(main.wordBreak(s2, List.of("leet", "code")));
        System.out.println(main.wordBreak(s, List.of("car", "ca", "rs")));
        System.out.println(main.wordBreak(s3, List.of("aaaa", "aa")));
        System.out.println(main.wordBreak(s4, List.of("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Node trie = buildTrie(wordDict);
        return traverseTrie(0, trie, s);
    }

    public boolean traverseTrie(int start, Node trie, String s) {
        Node cur = trie;
        for (int i = start; i < s.length(); i++) {
            char ch = s.charAt(i);
            Node node = cur.children.stream().filter(child -> child.val == ch).findAny().orElse(null);
            if (node == null) return false;
            if (node.isEnd && traverseTrie(i+1, trie, s)) return true;
            cur = node;
        }
        return cur.isEnd;
    }

    private Node buildTrie(List<String> wordDict) {
        Node root = new Node('#');
        Node cur = root;
        for (String word : wordDict) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                Node node = cur.children.stream().filter(n -> n.val == ch).findAny().orElse(null);
                if (node == null) {
                    node = new Node(word.charAt(i));
                    cur.children.add(node);
                }
                cur = node;
            }
            cur.isEnd = true;
            cur = root;
        }
        return root;
    }
}
