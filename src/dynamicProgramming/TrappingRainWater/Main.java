package dynamicProgramming.TrappingRainWater;

public class Main {
    public static void main(String[] args) {
        int[] heights = {5,1,3,1,1,1,2,1,4};
        System.out.println(trap(heights));
    }

    public static int trap(int[] heights) {
        int n = heights.length;
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];
        for (int i = 1, max = heights[0]; i < heights.length; i++) {
            maxLeft[i] = max;
            max = Math.max(max, heights[i]);
        }

        for (int i = n-2, max = heights[n-1]; i >= 0; i--) {
            maxRight[i] = max;
            max = Math.max(max, heights[i]);
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            int height = heights[i];
            int maxTrap = Math.min(maxLeft[i], maxRight[i]);
            answer += Math.max(0, maxTrap - height);
        }
        return answer;
    }
}
