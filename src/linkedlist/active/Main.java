package linkedlist.active;

import linkedlist.ListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class Main {
    HashMap<Node, Node> visited = new HashMap<>();
    public static void main(String[] args) {
        Main main = new Main();
        Node head = new Node(7);
        head.next = new Node(13);
        head.next.next = new Node(11);
        head.next.next.next = new Node(10);
        head.next.next.next.next = new Node(1);

        head.next.random = head;
        head.next.next.random = head.next.next.next.next;
        head.next.next.next.random = head.next.next;
        head.next.next.next.next.random = head;

        Node newHead = main.copyRandomListIterative(head);
        Node cur = newHead;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();

        cur = newHead;
        while (cur != null) {
            System.out.print((cur.random != null ? cur.random.val : "null") + " ");
            cur = cur.next;
        }

        System.out.println(newHead.next.next == head.next.next);
    }

    public Node copyRandomListIterative(Node head) {
        if (head == null) return null;
        Node original = head;
        while (original != null) {
            fillNode(original);
            original = original.next;
        }

        return visited.get(head);
    }

    private Node getOrPlaceNew(Node original) {
        if (original == null) return null;
        if (visited.containsKey(original)) return visited.get(original);
        Node node = new Node(original.val);
        visited.put(original, node);
        return node;
    }

    private Node fillNode(Node original) {
        Node copy = getOrPlaceNew(original);
        copy.next = getOrPlaceNew(original.next);
        copy.random = getOrPlaceNew(original.random);
        return copy;
    }
}
