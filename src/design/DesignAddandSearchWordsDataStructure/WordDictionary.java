package design.DesignAddandSearchWordsDataStructure;

class Node {
    char val;
    Node[] nodes;
    public Node(char val) {
        this.val = val;
        this.nodes = new Node[26];
    }
}

public class WordDictionary {
    private final Node[] nodes;
    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("a");
        wordDictionary.addWord("a");
        System.out.println(wordDictionary.search(".")); // return true
        System.out.println(wordDictionary.search("a")); // return true
        System.out.println(wordDictionary.search("aa")); // return false
        System.out.println(wordDictionary.search("a")); // return true
        System.out.println(wordDictionary.search(".a")); // return false
        System.out.println(wordDictionary.search("a.")); // return false
    }

    public WordDictionary() {
        this.nodes = new Node[26];
    }

    public void addWord(String word) {
        Node[] nodes = this.nodes;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int idx = ch - 'a';
            if (nodes[idx] == null) {
                nodes[idx] = new Node(ch);
            }
            Node cur = nodes[idx];
            nodes = cur.nodes;
        }
    }

    public boolean search(String word) {
        return find(0, word, this.nodes);
    }

    private boolean find(int start, String word, Node[] nodes) {
        for (int i = start; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch == '.') {
                for (Node node : nodes) {
                    if (node != null) {
                        boolean isFound = find(i+1, word, node.nodes);
                        if (isFound) return true;
                    }
                }
                return false;
            } else {
                int idx = ch - 'a';
                if (nodes[idx] == null) {
                    return false;
                } else {
                    return find(i+1, word, nodes[idx].nodes);
                }
            }
        }
        return true;
    }
}
