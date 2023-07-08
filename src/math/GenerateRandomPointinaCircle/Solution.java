package math.GenerateRandomPointinaCircle;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Solution {
    private final double radius;
    private final double x;
    private final double y;

    public static void main(String[] args) {
        Solution solution = new Solution(1.0, 0.0, 0.0);
        for (int i = 0; i < 43; i++) {
            System.out.println(Arrays.toString(solution.randPoint()));
        }
    }

    public Solution(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.x = x_center;
        this.y = y_center;
    }

    public double[] randPoint() {
        double xCur = ThreadLocalRandom.current().nextDouble(x-radius, x+radius);
        double yCur = ThreadLocalRandom.current().nextDouble(y-radius, y+radius);
        if (Math.sqrt(Math.pow(xCur, 2) + Math.pow(yCur, 2)) <= this.radius) return new double[]{xCur, yCur};
        return randPoint();
    }

}
