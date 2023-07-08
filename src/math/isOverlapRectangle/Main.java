package math.isOverlapRectangle;

public class Main {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int x1r1 = rec1[0];
        int y1r1 = rec1[1];
        int x2r1 = rec1[2];
        int y2r1 = rec1[3];
        int x1r2 = rec2[0];
        int y1r2 = rec2[1];
        int x2r2 = rec2[2];
        int y2r2 = rec2[3];

        if (x1r1 < x2r2 && x1r1 > x1r2) {
            if (y1r1 > y1r2 && y1r1 < y2r2) return true;
            if (y2r1 > y1r2 && y2r1 < y2r2) return true;
        }
        if (x2r1 < x2r2 && x2r1 > x1r2) {
            if (y1r1 > y1r2 && y1r1 < y2r2) return true;
            if (y2r1 > y1r2 && y2r1 < y2r2) return true;
        }
        return false;
    }
}
