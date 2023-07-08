package trie.MinimumGeneticMutation;

import java.util.ArrayList;
import java.util.List;

class Node {
    char val;
    List<Node> nodes;
    Node(char val) {
        this.val = val;
        this.nodes = new ArrayList<>();
    }
}

public class Main {
    public static void main(String[] args) {
        String[] bank = {"AACCGGTA"};
        String[] bank2 = {"AACCGGTA","AACCGCTA","AAACGGTA"};
        System.out.println(minMutation("AACCGGTT", "AACCGGTA", bank));
        System.out.println(minMutation("AACCGGTT", "AAACGGTA", bank2));
    }

    public static int minMutation(String startGene, String endGene, String[] bank) {
        Node trie = new Node('0');
        for (String candidate : bank) {
            Node cur = trie;
            for (char ch : candidate.toCharArray()) {
                Node node = cur.nodes.stream().filter(n -> n.val == ch).findAny().orElse(null);
                if (node == null) {
                    Node newNode = new Node(ch);
                    cur.nodes.add(newNode);
                    cur = newNode;
                } else {
                    cur = node;
                }
            }
        }

        int answer = 0;
        Node cur = trie;
        for (int i = 0; i < startGene.length(); i++) {
            char ch = endGene.charAt(i);
            Node node = cur.nodes.stream().filter(n -> n.val == ch).findAny().orElse(null);
            if (node == null) return -1;
            if (ch != startGene.charAt(i)) {
                answer++;
            }
            cur = node;
        }
        return answer;
    }
}
