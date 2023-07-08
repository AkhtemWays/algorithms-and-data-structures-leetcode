package bitManipulation.BinaryGap;

public class Main {
    public static void main(String[] args) {
//        System.out.println(binaryGap(22)); // 2
//        System.out.println(binaryGap(8)); // 0
//        System.out.println(binaryGap(5)); // 2
        System.out.println(binaryGap(12)); // 1
        System.out.println(binaryGap(1)); // 0
        System.out.println(binaryGap(2)); // 0
    }

    public static int binaryGap(int n) {
        int last = (n & ~(n-1)) - 1;
        int p = last + 1;
        int answer = 0;
        int todo = n >> p;
        if (todo == 0) return 0;
        while (todo != 0) {
            if ((todo & 1) == 1) {
                answer = Math.max(p - last, answer);
                last = p;
            }
            p++;
            todo >>= 1;
        }
        return answer;
    }
}
