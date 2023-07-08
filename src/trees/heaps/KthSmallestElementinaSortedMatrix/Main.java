package trees.heaps.KthSmallestElementinaSortedMatrix;

import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = {{1,5,9},{10,11,13},{12,14,15}};
        System.out.println(kthSmallest(matrix, 8));
    }

    public static int kthSmallest(int[][] matrix, int k) {
        if (k < 0 || matrix.length * matrix[0].length < k) return 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int cur = matrix[i][j];
                if (!heap.isEmpty() && cur < heap.peek()) {
                    k--;
                    heap.poll();
                }
                heap.add(cur);
            }
        }
        while (--k > 0) {
            heap.poll();
        }
        return heap.peek();
    }
}
