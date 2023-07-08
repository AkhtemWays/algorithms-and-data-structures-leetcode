package graphs.CriticalConnectionsinaNetwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tarjan {
    public static void main(String[] args) {
//        System.out.println(getSum(2, 3)); // 5
        System.out.println(getSum(19, 7)); // 26
    }

    public static int getSum(int a, int b) {
        int i = 0;
        int carry = a & b;
        int val = a ^ b;
        while ((carry >> i) > 0) {
            int mask = ((carry >> i) & 1) << i+1;
            val = val ^ mask;
            i++;
        }
        return val;
    }

    //  10011 = 19
    //  00111 = 7
    //  11010 = 26
    //  10100 - ^ = 20
    //  00011- & (carry) = 3
    //  010110
}
