package dynamicProgramming.TrappingRainWaterII;

public class Main {
    public static void main(String[] args) {

    }

    public int trapRainWater(int[][] heightMap) {
        int n = heightMap.length;
        int m = heightMap[0].length;
        int[][] maxLeft = new int[n][m];
        int[][] maxRight = new int[n][m];
        int[][] maxBottom = new int[n][m];
        int[][] maxTop = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 1, c = m-2, leftMax = heightMap[i][0], rightMax = heightMap[i][m-1]; j < m; j++, c--) {
                maxLeft[i][j] = leftMax;
                leftMax = Math.max(heightMap[i][j], leftMax);
                maxRight[i][c] = rightMax;
                rightMax = Math.max(heightMap[i][c], rightMax);
            }
        }

        for (int j = 0; j < m; j++) {
            for (int i = 1, c = n-2, bottomMax = heightMap[n-1][j], topMax = heightMap[0][j]; i < n; i++, c--) {
                maxBottom[c][j] = bottomMax;
                bottomMax = Math.max(bottomMax, heightMap[c][j]);
                maxTop[i][j] = topMax;
                topMax = Math.max(topMax, heightMap[i][j]);
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int height = heightMap[i][j];
                int maxTrap = Math.min(Math.min(maxLeft[i][j], maxRight[i][j]), Math.min(maxBottom[i][j], maxTop[i][j]));
                answer += Math.max(0, maxTrap - height);
            }
        }

        return answer;
    }
}
