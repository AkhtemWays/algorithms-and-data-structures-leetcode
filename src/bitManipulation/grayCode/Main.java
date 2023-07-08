package bitManipulation.grayCode;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        grayCode2(1).forEach(System.out::println);
    }

    public static List<Integer> grayCode(int n) {
        List<String> binaries = new ArrayList<>(List.of("0", "1"));
        for (int i = 2; i <= n; i++) {
            List<String> zeros = binaries.stream().map(e -> "0" + e).collect(Collectors.toList());
            List<String> ones = binaries.stream().map(e -> "1" + e).collect(Collectors.toList());
            Collections.reverse(ones);
            zeros.addAll(ones);
            binaries = new ArrayList<>(zeros);
        }
        return binaries.stream().map(binary -> Integer.parseInt(binary, 2)).collect(Collectors.toList());
    }

    public static List<Integer> grayCode2(int n) {
        List<Integer> binaries = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            binaries.add(i ^ (i >> 1));
        }
        return binaries;
    }
}
