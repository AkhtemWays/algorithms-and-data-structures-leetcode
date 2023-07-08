package arrays.FlippinganImage;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }

    public static int[][] flipAndInvertImage(int[][] image) {
        for (int[] arr : image) {
            reverse(arr);
            flip(arr);
        }
        return image;
    }

    private static void flip(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] == 0 ? 1 : 0;
        }
    }

    private static void reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            swap(array, i, array.length-1-i);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
