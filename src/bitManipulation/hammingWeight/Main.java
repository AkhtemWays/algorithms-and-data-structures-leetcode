package bitManipulation.hammingWeight;

public class Main {
    public static void main(String[] args) {

    }

    public static int hammingWeight(int n) {
        int count = n < 0 ? 1 : 0;
        for (int x = 0; x < 31; x++) {
            if ((n & (1 << x)) != 0) count++;
        }
        return count;
    }
}
