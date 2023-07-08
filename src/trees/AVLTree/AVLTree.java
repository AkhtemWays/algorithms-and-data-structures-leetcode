package trees.AVLTree;

class Node {
    int val;
    Node left;
    Node right;
    Node(int val) {
        this.val = val;
    }
}

public class AVLTree {

    Node root;
    AVLTree(int val) {
        this.root = new Node(val);
    }

    void insert(int val) {
        Node cur = root;
        Node prev = null;
        while (cur != null) {
            prev = cur;
            if (val >= cur.val) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        if (prev == null) {
            prev = cur;
        }
        if (val >= prev.val) {
            prev.right = new Node(val);
        } else {
            prev.left = new Node(val);
        }
    }

    void delete(int val) {
        Node cur = root;
        Node prev = null;
        while (cur != null) {
            if (cur.val == val) {
                Node left = cur.left;
                Node right = cur.right;
                if (left == null) {

                }
            }
            else if (cur.val > val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
    }

}
