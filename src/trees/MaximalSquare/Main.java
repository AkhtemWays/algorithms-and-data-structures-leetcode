package trees.MaximalSquare;

public class Main {
    private static void test1() {
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(maximalSquare(matrix));
    }
    private static void test2() {
        char[][] matrix = {{'0','1'},{'1','0'}};
        System.out.println(maximalSquare(matrix));
    }
    private static void test3() {
        char[][] matrix = {{'1','1'},{'1','1'}};
        System.out.println(maximalSquare(matrix));
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    private static int n;
    private static int m;

    private static int maximalSquare(char[][] matrix) {
        n = matrix.length;
        m = matrix[0].length;
        int[][] prefixSums = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0) {
                    prefixSums[i][j] = matrix[i][j] == '1' ? 1 : 0;
                } else {
                    prefixSums[i][j] = prefixSums[i][j-1] + (matrix[i][j] == '1' ? 1 : 0);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    answer = Math.max(answer, getMaxSquare(i, j, prefixSums));
                }
            }
        }
        return answer;
    }

    private static int getMaxSquare(int i, int j, int[][] prefixSums) {
        int answer = 1;
        int width = Integer.MAX_VALUE;
        for (int k = i; k < n; k++) {
            width = Math.min(width, binarySearch(j, prefixSums[k]));
            int height = k - i + 1;
            if (height <= width) {
                answer = Math.max(answer, height * height);
            } else {
                break;
            }
        }
        return answer;
    }

    private static int binarySearch(int j, int[] prefixSum) {
        int l = j, r = m;
        while (l < r) {
            int mid = (l + r) / 2;
            int sum = prefixSum[mid] - (j > 0 ? prefixSum[j-1] : 0);
            if (sum == mid - j + 1) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l == m ? prefixSum[m-1] - (j > 0 ? prefixSum[j-1] : 0) : prefixSum[l] - (j > 0 ? prefixSum[j-1] : 0);
    }
}
