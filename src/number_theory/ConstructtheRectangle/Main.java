package number_theory.ConstructtheRectangle;

public class Main {
    public static void main(String[] args) {

    }

    public static int[] constructRectangle(int area) {
        int width = (int) Math.sqrt(area);
        while (area % width != 0) {
            width--;
        }
        return new int[]{area/width, width};
    }
}
