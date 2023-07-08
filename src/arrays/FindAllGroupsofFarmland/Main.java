package arrays.FindAllGroupsofFarmland;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static void test1() {
        int[][] land = {{1,1},{1,1}};
        System.out.println(Arrays.deepToString(findFarmland(land)));
    }
    private static void test2() {
        int[][] land = {{1,0,0},{0,1,1},{0,1,1}};
        System.out.println(Arrays.deepToString(findFarmland(land)));
    }
    public static void main(String[] args) {
        test1(); // [0,0,1,1]
        test2();
    }

    public static int[][] findFarmland(int[][] land) {
        int n = land.length;
        int m = land[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1) {
                    land[i][j] = j-1 >= 0 && land[i][j-1] > 0 ? land[i][j-1] + 1 : 1;
                }
            }
        }

        List<int[]> answer = new ArrayList<>();
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] > 0 && !visited[i][j]) {
                    int k = j;
                    while (k < m && land[i][k] > 0 && !visited[i][k]) {
                        visited[i][k] = true;
                        k++;
                    }
                    int width = land[i][k-1];
                    int endJ = k-1;
                    k = i + 1;
                    while (k < n && land[k][endJ] >= width && !visited[k][endJ]) {
                        for (int c = 0; c < width; c++) {
                            visited[k][endJ-c] = true;
                        }
                        k++;
                    }
                    int endI = k-1;
                    answer.add(new int[]{i, j, endI, endJ});
                }
            }
        }

        int[][] res = new int[answer.size()][4];
        for (int i = 0; i < answer.size(); i++) res[i] = answer.get(i);
        return res;
    }
}
