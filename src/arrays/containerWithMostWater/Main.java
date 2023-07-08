package arrays.containerWithMostWater;

public class Main {
    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }

    public static int maxArea(int[] height) {
        int maxArea = 0;
        int x1 = 0;
        int x2 = height.length - 1;
        while (x1 != x2) {
            int y1 = height[x1];
            int y2 = height[x2];
            int yMin = Math.min(y1, y2);
            int candidateArea = yMin * (x2 - x1);
            if (candidateArea > maxArea) {
                maxArea = candidateArea;
            }
            if (yMin == y1) {
                x1 += 1;
            } else {
                x2 -= 1;
            }
        }
        return maxArea;
    }
}
