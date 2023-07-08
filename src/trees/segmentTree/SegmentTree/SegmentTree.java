package trees.segmentTree.SegmentTree;

import java.io.*;
public class SegmentTree{
    int[] tree;

    SegmentTree(int n){
        int height = (int)(Math.log(n)/Math.log(2)) + 1;
        int tree_nodes = (int) Math.pow(2, height);
        tree = new int[tree_nodes];
    }

    void build(int[] arr, int node, int start, int end){
        if(start == end){
            tree[node] = arr[start];
        }
        else{
            int mid = (start + end)/2;
            build(arr, 2*node + 1, start, mid);
            build(arr, 2*node + 2, mid + 1, end);
            tree[node] = tree[2*node + 1] + tree[2*node + 2];
        }
    }

    void update(int[] arr, int node, int index, int val, int start, int end){
        if(start == end){
            tree[node] += val;
            arr[start] += val;
        }
        else{
            int mid = (start + end)/2;
            if(start <= index && index <= mid){
                update(arr, 2*node + 1, index, val, start, mid);
            }
            else{
                update(arr, 2*node + 2, index, val, mid + 1, end);
            }
            tree[node] = tree[2*node + 1] + tree[2*node + 2];
        }
    }

    // left and right refers to search interval
    // start end refers to binary search
    int query(int node, int start, int end, int left, int right){
        if(right < start || end < left){
            return 0;
        }
        if(left <= start && end <= right){
            return tree[node];
        }
        int mid = (start + end)/2;
        int p1 = query(2*node + 1, start, mid, left, right);
        int p2 = query(2*node + 2, mid + 1, end, left, right);
        return p1 + p2;
    }
}

class Test{
    public static void main(String[] args){
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int n = arr.length;
        SegmentTree ob = new SegmentTree(n);
        ob.build(arr, 0, 0, n - 1);
        for(int i = 0; i < ob.tree.length; i++) {
            System.out.print(ob.tree[i] + " ");
        }
        System.out.println();
        System.out.println(ob.query(0, 0, n - 1, 0, 5));
    }
}
