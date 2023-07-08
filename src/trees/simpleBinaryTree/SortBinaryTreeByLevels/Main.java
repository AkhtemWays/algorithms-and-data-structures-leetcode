package trees.simpleBinaryTree.SortBinaryTreeByLevels;

import java.util.*;

class Node {
    public Node left;
    public Node right;
    public int value;

    public Node(int v) {
        value = v;
    }

    public Node(Node l, Node r, int v) {
        left = l;
        right = r;
        value = v;
    }
}

public class Main {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node3.left = node5;
        node4.left = node6;
        node5.left = node7;
        node5.right = node8;
        treeByLevels(node1).forEach(System.out::println);
    }

    public static List<Integer> treeByLevels(Node node) {
        if (node == null) return List.of();
        Queue<Node> parents = new LinkedList<>();
        parents.add(node);

        return bfs(parents);
    }

    static List<Integer> bfs(Queue<Node> parents) {
        List<Integer> res = new ArrayList<>();
        while (!parents.isEmpty()) {
            Node parent = parents.poll();
            res.add(parent.value);
            Node leftChild = parent.left;
            Node rightChild = parent.right;
            if (leftChild != null) parents.add(leftChild);
            if (rightChild != null) parents.add(rightChild);
        }
        return res;
    }
}
