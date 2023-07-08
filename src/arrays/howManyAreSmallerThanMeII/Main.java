package arrays.howManyAreSmallerThanMeII;

import java.util.*;

class Node {
    int value;
    Node leftChild;
    Node rightChild;
    int leftRange;
    int rightRange;

    public Node(int value) {
        this.value = value;
    }
}

class AkhtemTree {
    Node root;
    public void insert(int value) {
        Node node = root;
        while (true) {
            if (value < node.value) {
                node.leftRange++;
                if (node.leftChild != null) {
                    node = node.leftChild;
                } else {
                    node.leftChild = new Node(value);
                    return;
                }
            } else {
                node.rightRange++;
                if (node.rightChild != null) {
                    node = node.rightChild;
                } else {
                    Node rightChild = new Node(value);
                    rightChild.leftRange = node.leftRange + 1;
                    node.rightChild = rightChild;
                    return;
                }
            }
        }
    }

    public Node find(int value) {
        if (root == null) return null;
        Node node = root;
        while (node.value != value) {
            if (value < node.value) {
                node = node.leftChild;
            } else {
                node = node.rightChild;
            }
        }
        return node;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] unsorted1 = {1, 2, 0}; // [1, 1, 0]
        int[] unsorted2 = {5,4,3,2,1}; // [4, 3, 2, 1, 0]
        int[] unsorted3 = {1,2,3}; // [0, 0, 0]
        int[] unsorted4 = {1,2,1}; // [0,1,0]
        int[] unsorted5 = {5, 4, 7, 9, 2, 4, 4, 5, 6}; // 4, 1, 5, 5, 0, 0, 0, 0, 0
        int[] unsorted6 = {1,1,-1,0,0}; // 3,3,0,0,0
        int[] unsorted7 = {5, 4, 7, 9, 2, 4, 1, 4, 5, 6}; // 5, 2, 6, 6, 1, 1, 0, 0, 0, 0
        System.out.println(Arrays.toString(smaller(unsorted1)));
        System.out.println(Arrays.toString(smaller(unsorted2)));
        System.out.println(Arrays.toString(smaller(unsorted3)));
        System.out.println(Arrays.toString(smaller(unsorted4)));
        System.out.println(Arrays.toString(smaller(unsorted5)));
        System.out.println(Arrays.toString(smaller(unsorted6)));
        System.out.println(Arrays.toString(smaller(unsorted7)));
    }

    public static int[] smaller(int[] unsorted) {
        int[] sorted = Arrays.copyOf(unsorted, unsorted.length);
        Arrays.sort(sorted);
        int[] res = new int[unsorted.length]; // [5, 4, 3, 2, 1] -> [4, 3, 2, 1, 0]
        AkhtemTree unsortedTree = new AkhtemTree();
        AkhtemTree sortedTree = new AkhtemTree();
        sortedTree.root = new Node(sorted[0]);
        for (int i = 1; i < sorted.length; i++) sortedTree.insert(sorted[i]);

        for (int i = 0; i < unsorted.length; i++) {
            int val = unsorted[i];
            int idx = sortedTree.find(val).leftRange;
            if (i == 0) {
                unsortedTree.root = new Node(val);
            } else {
                unsortedTree.insert(val);
            }
            Node node = unsortedTree.find(val);
            res[i] = i == unsorted.length - 1 ? 0 : idx - node.leftRange;
        }

        return res;
    }
}
