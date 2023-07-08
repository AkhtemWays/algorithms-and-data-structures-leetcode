package graphs.cloneGraph;

import java.util.ArrayList;
import java.util.List;

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class Main {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors = new ArrayList<>(List.of(node2, node4));
        node2.neighbors = new ArrayList<>(List.of(node1, node3));
        node3.neighbors = new ArrayList<>(List.of(node2, node4));
        node4.neighbors = new ArrayList<>(List.of(node3, node1));
        System.out.println(cloneGraph(node1));
    }

    public static Node cloneGraph(Node node) {
        if (node == null) return null;
        Node[] nodes = new Node[300];
        Node copy = new Node(node.val);
        bfs(node, copy, nodes);

        return copy;
    }

    public static void bfs(Node original, Node copy, Node[] nodes) {
        nodes[original.val] = copy;

        for (Node neighbor : original.neighbors) {
            if (nodes[neighbor.val] == null) {
                Node copyNeighbor = new Node(neighbor.val);
                copy.neighbors.add(copyNeighbor);
                bfs(neighbor, copyNeighbor, nodes);
            } else {
                copy.neighbors.add(nodes[neighbor.val]);
            }
        }
    }
}
