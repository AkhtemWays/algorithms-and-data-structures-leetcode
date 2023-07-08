package math.MinimumAreaRectangle;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[][] points = {{1,1},{1,3},{3,1},{2,2}};
        System.out.println(main.minAreaRect(points));
    }

    public int minAreaRect(int[][] points) {
        if (points.length < 4) return 0;
        HashMap<Integer, Set<Integer>> x = new HashMap<>();
        for (int[] point : points) {
            if (x.containsKey(point[0])) {
                x.get(point[0]).add(point[1]);
            } else {
                x.put(point[0], new HashSet<>(List.of(point[1])));
            }
        }
        int minArea = Integer.MAX_VALUE;

        for (int i = 0; i < points.length; i++) {
            int[] point = points[i];
            for (int j = i+1; j < points.length; j++) {
                int[] diagonalPoint = points[j];
                if (diagonalPoint[0] != point[0] && diagonalPoint[1] != point[1]) {
                    if (x.containsKey(diagonalPoint[0]) && x.get(diagonalPoint[0]).contains(point[1]) &&
                        x.containsKey(point[0]) && x.get(point[0]).contains(diagonalPoint[1])) {
                        minArea = Math.min(minArea, Math.abs(diagonalPoint[0] - point[0]) * Math.abs(diagonalPoint[1] - point[1]));
                    }
                }
            }
        }
        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }
}
