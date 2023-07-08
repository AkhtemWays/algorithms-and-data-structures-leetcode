package math.rectangleArea;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println(computeArea2(-3, 0, 3, 4, 0, -1, 9, 2)); // 45
        System.out.println(computeArea2(-2, -2, 2, 2, -2, -2, 2, 2)); // 16
        System.out.println(computeArea2(-2, -2, 2, 2, 3, 3, 4, 4)); // 17
    }

    public static int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        Set<Integer> ax = new HashSet<>();
        for (int i = ax1; i <= ax2; i++) ax.add(i);
        Set<Integer> ay = new HashSet<>();
        for (int i = ay1; i <= ay2; i++) ay.add(i);
        int firstArea = (ax.size() - 1) * (ay.size() - 1);


        Set<Integer> bx = new HashSet<>();
        for (int i = bx1; i <= bx2; i++) bx.add(i);
        Set<Integer> by = new HashSet<>();
        for (int i = by1; i <= by2; i++) by.add(i);
        int secondArea = (bx.size() - 1) * (by.size() - 1);

        ax.retainAll(bx);
        ay.retainAll(by);
        int intersectionArea = ax.size() == 0 || ay.size() == 0 ? 0 : (ax.size() - 1) * (ay.size() - 1);

        return firstArea + secondArea - intersectionArea;
    }

    public static int computeArea2(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
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
