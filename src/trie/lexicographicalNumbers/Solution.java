package trie.lexicographicalNumbers;

import java.util.*;

class Node {
    int val;
    boolean isEnd = false;
    Comparator<Node> comparator = Comparator.comparingInt((Node a) -> a.val);
    Set<Node> nodes = new TreeSet<>(comparator);
    public Node() {}
    public Node(int val) {
        this.val = val;
    }
}
class Trie {
    Comparator<Node> comparator = Comparator.comparingInt((Node a) -> a.val);
    Set<Node> nodes = new TreeSet<>(comparator);

    public Trie() {
        for (int i = 1; i < 10; i++) nodes.add(new Node(i));
    }
}

public class Solution {
    public static void main(String[] args) {
        System.out.println(lexicalOrder(10));
    }

    public static List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        if (n < 10) {
            for (int i = 1; i <= n; i++) result.add(i);
            return result;
        }

        Trie trie = new Trie();
        fillTrie(n, trie.nodes);
        for (Node node : trie.nodes) {
            preOrderTraversal(node, result);
        }
        return result;
    }

    static void fillTrie(int n, Set<Node> nodes) {
        for (Node node : nodes) {
            for (int i = 0; i < 10; i++) {
                int value = node.val * 10 + i;
                if (value <= n) {
                    Node newNode = new Node(value);
                    node.nodes.add(newNode);
                } else return;
            }
            fillTrie(n, node.nodes);
        }
    }

    static void preOrderTraversal(Node node, List<Integer> result) {
        result.add(node.val);
        for (Node n : node.nodes) {
            preOrderTraversal(n, result);
        }
    }
}
