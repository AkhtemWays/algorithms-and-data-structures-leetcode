package arrays.PourWater;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] heights = {2,1,1,2,1,2,2};
        int[] heights2 = {3,1,3};
        int[] heights3 = {1,2,3,4,3,2,1,2,3,4,3,2,1};
                       // 1,2,3,4,3,3,2,2,3,4,3,2,1
        int[] heights4 = {1,2,3,4,3,2,1,2,3,4,3,2,1};
//                        1,2,3,4,3,4,3,3,3,4,3,2,1
        int[] heights5 = {1,2,3,4,3,2,1,2,3,4,3,2,1};
//                        4,4,4,4,3,3,3,3,3,4,3,2,1
        System.out.println(Arrays.toString(pourWater(heights, 4, 3)));
        System.out.println(Arrays.toString(pourWater(heights2, 5, 1)));
        System.out.println(Arrays.toString(pourWater(heights3, 2, 5)));
        System.out.println(Arrays.toString(pourWater(heights4, 5, 5)));
        System.out.println(Arrays.toString(pourWater(heights5, 10, 2)));
    }

    public static int[] pourWater(int[] heights, int volume, int k) {
        for (int i = 0; i < volume; i++) {
            int leftIdx = findIndex(true, heights, k);
            if (leftIdx != -1 && heights[leftIdx] < heights[k]) {
                heights[leftIdx]++;
            } else {
                int rightIdx = findIndex(false, heights, k);
                if (rightIdx != -1 && heights[rightIdx] < heights[k]) {
                    heights[rightIdx]++;
                } else {
                    heights[k]++;
                }
            }
        }
        return heights;
    }

    private static int findIndex(boolean left, int[] heights, int k) {
        int elem = heights[k];
        int minValue = Integer.MAX_VALUE;
        int idx = -1;
        if (left) {
            for (int i = k-1; i >= 0; i--) {
                if (heights[i] > elem) {
                    break;
                }
                else if (heights[i] < elem) {
                    idx = i;
                    break;
                }
            }
            return idx;
        }
        for (int i = k+1; i < heights.length; i++) {
            if (heights[i] > elem) {
                break;
            }
            else if (heights[i] < elem) {
                idx = i;
                break;
            }
        }
        return idx;
    }
}
