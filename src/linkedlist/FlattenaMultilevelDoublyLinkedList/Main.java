package linkedlist.FlattenaMultilevelDoublyLinkedList;

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
    Node(int val) {
        this.val = val;
    }
};

public class Main {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.prev = head;
        head.next.next = new Node(3);
        head.next.next.next.prev = head.next.next;
        head.next.next.next = new Node(4);
        head.next.next.next.next.prev = head.next.next.next;
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next.prev = head.next.next.next.next;
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next.prev = head.next.next.next.next.next;
        Node firstChild = new Node(7);
        head.next.next.child = firstChild;
        firstChild.next = new Node(8);
        firstChild.next.prev = firstChild;
        firstChild.next.next = new Node(9);
        firstChild.next.next.next.prev = firstChild.next.next;
        firstChild.next.next.next = new Node(10);
        firstChild.next.next.next.next.prev = firstChild.next.next.next;
        Node secondChild = new Node(11);
        firstChild.next.child = secondChild;
        secondChild.next = new Node(12);
        secondChild.next.prev = secondChild;
        flatten(head);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static Node flatten(Node head) {
        if (head == null) return null;
        flat(head);
        return head;
    }

    private static Node flat(Node cur) {
        if (cur.child != null) {
            Node next = cur.next;
            Node nextTail = flat(cur.next);
            Node childTail = flat(cur.child);
            cur.next = cur.child;
            cur.child.prev = cur;
            childTail.next = next;
            next.prev = childTail;
            cur.child = null;
            cur = nextTail;
        }
        if (cur.next == null) return cur;
        return flat(cur.next);
    }
}
