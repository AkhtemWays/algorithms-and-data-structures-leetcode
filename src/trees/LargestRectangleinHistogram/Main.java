package trees.LargestRectangleinHistogram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3}; // 10
        System.out.println(largestRectangleArea(heights));
    }

    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int answer = 0;
        int[][] indexedHeights = new int[n][2];
        for (int i = 0; i < n; i++) {
            indexedHeights[i][0] = heights[i];
            indexedHeights[i][1] = i;
        }
        Arrays.sort(indexedHeights, (a, b) -> a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0]);
        TreeSet<Integer> bst = new TreeSet<>();
        int i = 0;
        while (i < n) {
            int height = indexedHeights[i][0];
            List<Integer> indices = new ArrayList<>();
            while (i < n && indexedHeights[i][0] == height) {
                indices.add(indexedHeights[i][1]);
                i++;
            }
            for (int idx : indices) {
                int lower = 0;
                int upper = n-1;
                Integer lowerBound = bst.lower(idx);
                Integer upperBound = bst.higher(idx);
                if (lowerBound != null) lower = lowerBound+1;
                if (upperBound != null) upper = upperBound-1;
                int width = upper - lower + 1;
                answer = Math.max(answer, height * width);
            }
            bst.addAll(indices);
        }
        return answer;
    }
}
