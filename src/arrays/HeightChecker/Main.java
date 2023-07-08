package arrays.HeightChecker;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }

    public int heightChecker(int[] heights) {
        int[] copy = Arrays.copyOf(heights, heights.length);
        Arrays.sort(copy);
        int c = 0;
        for (int i = 0; i < copy.length; i++) {
            if (copy[i] != heights[i]) c++;
        }
        return c;
    }
}
