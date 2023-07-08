package arrays.ImageSmoother;

public class Main {
    public static void main(String[] args) {

    }

    public static int[][] imageSmoother(int[][] img) {
        int[][] smoothed = new int[img.length][img[0].length];
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[i].length; j++) {
                smoothed[i][j] = smooth(img, i, j);
            }
        }
        return smoothed;
    }

    private static int smooth(int[][] img, int i, int j) {
        int count = img[i][j];
        int amount = 1;
        if (i+1 < img.length) {
            count += img[i+1][j];
            amount++;
            if (j+1 < img[i].length) {
                count += img[i+1][j+1];
                amount++;
            }
            if (j-1 >= 0) {
                count += img[i+1][j-1];
                amount++;
            }
        }
        if (j+1 < img[i].length) {
            count += img[i][j+1];
            amount++;
        }
        if (i-1 >= 0) {
            count += img[i-1][j];
            amount++;
            if (j+1 < img[i].length) {
                count += img[i-1][j+1];
                amount++;
            }
            if (j-1 >= 0) {
                count += img[i-1][j-1];
                amount++;
            }
        }
        if (j-1 >= 0) {
            count += img[i][j-1];
            amount++;
        }
        return Math.floorDiv(count, amount);
    }
}
