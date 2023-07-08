package arrays.TrappingRainWater;

public class Main {
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));
    }

    public static int trap(int[] height) {
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];
        int count = 0;
        for (int i = 1; i < height.length; i++) {
            if (height[i] > maxLeft[i-1]) maxLeft[i] = height[i];
            else maxLeft[i] = maxLeft[i-1];
        }
        for (int i = height.length - 2; i >= 0; i--) {
            if (height[i] > maxRight[i+1]) maxRight[i] = height[i];
            else maxRight[i] = maxRight[i+1];
        }
        for (int i = 0; i < height.length; i++) {
            count += Math.max(Math.min(maxLeft[i], maxRight[i]) - height[i], 0);
        }
        return count;
    }
}
