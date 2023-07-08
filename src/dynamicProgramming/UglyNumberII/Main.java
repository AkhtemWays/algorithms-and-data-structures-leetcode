package dynamicProgramming.UglyNumberII;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.nthUglyNumber(11));
    }

    public int nthUglyNumber(int n) {
        if (n <= 1) return n;
        int count = 1;
        int num = 2;
        while (count < n) {
            if (num % 2 == 0 || num % 3 == 0 || num % 5 == 0) count++;
            num++;
        }
        return num-1;
    }
}
