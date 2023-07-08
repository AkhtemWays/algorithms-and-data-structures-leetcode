package trie.implementTrie;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class Node {
    char val;
    boolean isEnd = false;
    Comparator<Node> comparator = Comparator.comparingInt((Node a) -> a.val);
    Set<Node> nodes = new TreeSet<>(comparator);
    public Node() {}
    public Node(char val) {
        this.val = val;
    }
}
public class Trie {
    Comparator<Node> comparator = Comparator.comparingInt((Node a) -> a.val);
    Set<Node> nodes = new TreeSet<>(comparator);

    public Trie() {

    }

    public void insert(String word) {
        Set<Node> nodes = this.nodes;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (nodes.contains(new Node(letter))) {
                Node node = nodes.stream().filter((Node n) -> n.val == letter).findAny().orElse(null);
                assert node != null;
                if (i == word.length() - 1) node.isEnd = true;
                nodes = node.nodes;
            } else {
                Node node = new Node(letter);
                if (i == word.length() - 1) node.isEnd = true;
                nodes.add(node);
                nodes = node.nodes;
            }
        }
    }

    public boolean search(String word) {
        int i = 0;
        Set<Node> nodes = this.nodes;
        while (i < word.length()) {
            char letter = word.charAt(i);
            Node node = nodes.stream().filter((Node n) -> n.val == letter).findAny().orElse(null);
            if (node == null) return false;
            if (i == word.length() - 1) return node.isEnd;
            nodes = node.nodes;
            i++;
        }
        return true;
    }

    public boolean startsWith(String prefix) {
        int i = 0;
        Set<Node> nodes = this.nodes;
        while (i < prefix.length()) {
            char letter = prefix.charAt(i);
            Node node = nodes.stream().filter((Node n) -> n.val == letter).findAny().orElse(null);
            if (node == null) return false;
            nodes = node.nodes;
            i++;
        }
        return true;
    }
}
