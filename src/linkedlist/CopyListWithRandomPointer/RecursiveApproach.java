package linkedlist.CopyListWithRandomPointer;

import java.util.HashMap;

class Node implements Comparable {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) return -1;
        if (o instanceof Node) {
            Node n = (Node) o;
            return this.val - n.val;
        }
        return 0;
    }
}

public class RecursiveApproach {
    private static void printRandoms(Node node) {
        System.out.println("value: " + node.val);
        while (node.random != null) {
            System.out.println(node.random.val);
            node = node.random;
        }
    }

    private static void printNexts(Node node) {
        System.out.println("value: " + node.val);
        while (node.next != null) {
            System.out.println(node.next.val);
            node = node.next;
        }
    }

    public static void main(String[] args) {
        Node node7 = new Node(7);
        Node node13 = new Node(13);
        Node node11 = new Node(11);
        Node node10 = new Node(10);
        Node node1 = new Node(1);
        node1.random = node7;
        node10.next = node1;
        node10.random = node11;
        node11.next = node10;
        node11.random = node1;
        node13.random = node7;
        node13.next = node11;
        node7.next = node13;

        Node node7Copy = copyRandomList(node7);
        Node node13Copy = node7Copy.next;
        Node node11Copy = node13Copy.next;
        Node node10Copy = node11Copy.next;
        Node node1Copy = node10Copy.next;

        printRandoms(node10Copy);

    }

    public static Node copyRandomList(Node head) {
        HashMap<Node, Node> store = new HashMap<>();
        return dfs(head, store);
    }

    private static Node dfs(Node original, HashMap<Node, Node> store) {
        if (original == null) return null;

        if (store.containsKey(original)) return store.get(original);

        Node copy = new Node(original.val);
        store.put(original, copy);
        copy.next = dfs(original.next, store);
        copy.random = dfs(original.random, store);
        return copy;
    }
}
