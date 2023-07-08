package math.binomialExpansion;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(expand("(x+1)^2")); // "x^2+2x+1"
        System.out.println(expand("(p-1)^3")); // "p^3-3p^2+3p-1"
        System.out.println(expand("(2f+4)^6")); // "64f^6+768f^5+3840f^4+10240f^3+15360f^2+12288f+4096"
        System.out.println(expand("(r+0)^203")); // "r^203"
        System.out.println(expand("(-2a-4)^0")); // "1"
    }

    public static String expand(String expr) {
        System.out.println(Arrays.toString(expr.split("[^]")));
        return "";
    }
}
