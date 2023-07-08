package trees.CloneNaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
    public int val;
    public List<Node> children;


    public Node() {
        children = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }

    public Node(int _val,ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
}

public class Main {
    public static void main(String[] args) {

    }

    public static Node cloneTree(Node root) {
        if (root == null) return null;

        Queue<Node> q = new LinkedList<>(root.children);
        Node parent = new Node(root.val);
        Node finalCopy = parent;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Node node = q.poll();
                Node cur = new Node(node.val);
                parent.children.add(cur);
            }
        }
        return finalCopy;
    }
}
