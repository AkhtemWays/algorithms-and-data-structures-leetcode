package design.AllOne;

import java.util.HashMap;

public class AllOne {
    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        allOne.inc("hello");
        allOne.inc("goodbye");
        allOne.inc("hello");
        allOne.inc("hello");
        System.out.println(allOne.getMaxKey()); // return "hello"
        allOne.inc("leet");
        allOne.inc("code");
        allOne.inc("leet");
        allOne.dec("hello");
        allOne.inc("leet");
        allOne.inc("code");
        allOne.inc("code");
        System.out.println(allOne.getMaxKey()); // return "leet"
    }
    private class DoublyLinkedList {
        int val;
        String key;
        DoublyLinkedList prev;
        DoublyLinkedList next;
        DoublyLinkedList(int val, String key) {
            this.val = val;
            this.key = key;
        }
    }
    private final HashMap<String, DoublyLinkedList> stringCounts;
    DoublyLinkedList head;
    DoublyLinkedList tail;

    public AllOne() {
        stringCounts = new HashMap<>();
        head = new DoublyLinkedList(Integer.MIN_VALUE, "");
        tail = new DoublyLinkedList(Integer.MAX_VALUE, "");

        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {
        if (stringCounts.containsKey(key)) {
            DoublyLinkedList node = stringCounts.get(key);
            node.val++;
            shiftNodeRight(node);
        } else {
            DoublyLinkedList node = createNode(key);
            stringCounts.put(key, node);
        }
    }

    public void dec(String key) {
        if (stringCounts.containsKey(key)) {
            DoublyLinkedList node = stringCounts.get(key);
            if (node.val == 1) {
                removeNode(node);
                stringCounts.remove(key);
            } else {
                node.val--;
                shiftNodeLeft(node);
            }
        }
    }

    public String getMaxKey() {
        return tail.prev.key;
    }

    public String getMinKey() {
        return head.next.key;
    }

    private DoublyLinkedList createNode(String key) {
        DoublyLinkedList node = new DoublyLinkedList(1, key);
        DoublyLinkedList tmp = head.next;
        head.next = node;
        node.prev = head;
        node.next = tmp;
        tmp.prev = node;
        return node;
    }

    private void removeNode(DoublyLinkedList node) {
        DoublyLinkedList prev = node.prev;
        DoublyLinkedList next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private void shiftNodeRight(DoublyLinkedList node) {
        while (node.val > node.next.val) {
            swap(node, node.next);
        }
    }

    private void shiftNodeLeft(DoublyLinkedList node) {
        while (node.val < node.prev.val) {
            swap(node, node.prev);
        }
    }

    private void swap(DoublyLinkedList node1, DoublyLinkedList node2) {
        DoublyLinkedList node1Prev = node1.prev;
        DoublyLinkedList node1Next = node1.next;
        DoublyLinkedList node2Prev = node2.prev;

        node1.next = node2.next;
        node1.prev = node2Prev;
        node2.next = node1Next;
        node2.prev = node1Prev;
    }
}
