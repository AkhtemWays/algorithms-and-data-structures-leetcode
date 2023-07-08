package greedy.Candy;

public class Main {
    public static void main(String[] args) {
        int[] ratings = {1,2,2};
        int[] ratings2 = {1,0,2};
        System.out.println(candy(ratings));
        System.out.println(candy(ratings2));
    }

    public static int candy(int[] ratings) {
        int[] left2right = new int[ratings.length];
        int[] right2left = new int[ratings.length];
        left2right[0] = 1;
        right2left[right2left.length-1] = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i-1]) {
                left2right[i] = left2right[i-1] + 1;
            }
        }

        for (int i = right2left.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1]) {
                right2left[i] = right2left[i+1] + 1;
            }
        }
        int sum = 0;
        for (int i = 0; i < ratings.length; i++) {
            sum += Math.max(left2right[i], right2left[i]);
        }
        return sum;
    }
}
