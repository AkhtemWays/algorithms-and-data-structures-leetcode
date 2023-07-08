package math.totalAreaCoveredByRectangles;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int[][] rectangles = {{8, 0, 9, 7}, {2, 1, 4, 8}, {2, 2, 6, 5}, {2, 0, 6, 1}};
        int[][] rectangles2 = {{0, 4, 11, 6}};
        int[][] rectangles3 = {};
        int[][] rectangles4 = {{3,3,8,5}, {6,3,8,9}, {11,6,14,12}};
        int[][] rectangles5 = {{0,0,1000000000,1000000000}};
        System.out.println(calculateSpace(rectangles));
        System.out.println(calculateSpace(rectangles2));
        System.out.println(calculateSpace(rectangles3));
        System.out.println(calculateSpace(rectangles4));
        System.out.println(calculateSpace(rectangles5));
    }

    private static int baseCase(int[] rectangle) {
        return (rectangle[2] - rectangle[0])*(rectangle[3] - rectangle[1]);
    }

    public static int calculateSpace(int[][] rectangles) {
        int area = 0;
        if (rectangles.length == 0) return area;
        if (rectangles.length == 1) return baseCase(rectangles[0]);
        Arrays.sort(rectangles, (a, b) -> {
            if (a[0] - b[0] != 0) return a[0] - b[0];
            if (a[2] - b[2] != 0) return a[2] - b[2];
            if (a[1] - b[1] != 0) return a[1] - b[1];
            return a[3] - b[3];
        });
        List<Integer> xs = getXS(rectangles);
        for (int i = 0; i < xs.size() - 1; i++) {
            final int xmin = xs.get(i);
            final int xmax = xs.get(i+1);
            List<int[]> intervalRectangles = findAllIntervalRectangles(xmin, rectangles);
            if (!intervalRectangles.isEmpty()) {
                int y1r1 = intervalRectangles.get(0)[1];
                int y2r1 = intervalRectangles.get(0)[3];
                if (intervalRectangles.size() == 1) {
                    area += baseCase(new int[]{xmin, y1r1, xmax, y2r1});
                    continue;
                }
                int curArea = 0;
                for (int k = 1; k < intervalRectangles.size(); k++) {
                    int[] nextRectangle = intervalRectangles.get(k);
                    int y1r2 = nextRectangle[1];
                    int y2r2 = nextRectangle[3];
                    curArea += computeArea(xmin, y1r1, xmax, y2r1, xmin, y1r2, xmax, y2r2) - curArea;
                    y1r1 = Math.min(y1r1, y1r2);
                    y2r1 = Math.max(y2r1, y2r2);
                }
                area += curArea;
            }
        }
        return area;
    }

    private static List<int[]> findAllIntervalRectangles(int x1, int[][] rectangles) {
        List<int[]> res = new ArrayList<>();
        for (int[] rectangle : rectangles) {
            if (x1 >= rectangle[0] && x1 < rectangle[2]) res.add(rectangle);
        }
        return res;
    }

    private static List<Integer> getXS(int[][] rectangles) {
        TreeSet<Integer> s = new TreeSet<>();
        for (int[] rectangle : rectangles) {
            s.add(rectangle[0]);
            s.add(rectangle[2]);
        }
        return new ArrayList<>(s).stream().sorted().collect(Collectors.toList());
    }

    public static int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int a1=(ax2-ax1)*(ay2-ay1);//area of rectangle 1
        int a2=(bx2-bx1)*(by2-by1);//area of rectangle 2

        //co-ordinates of intersecting rectangle
        int x5 = Math.max(ax1, bx1);
        int y5 = Math.max(ay1, by1);
        int x6 = Math.min(ax2, bx2);
        int y6 = Math.min(ay2, by2);

        int x=x6-x5;
        int y=y6-y5;
        int a3=x*y;//area of intersecting rectangle
        if(x<0 || y<0){//means both rectangle don't intersects
            a3=0;
        }

        return a1+a2-a3;
    }
}
