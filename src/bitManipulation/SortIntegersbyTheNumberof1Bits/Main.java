package bitManipulation.SortIntegersbyTheNumberof1Bits;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println(Integer.bitCount(244));
    }

    public int[] sortByBits(int[] arr) {
        StringBuilder sb = new StringBuilder();

        List<Integer> l =  Arrays.stream(arr).boxed().collect(Collectors.toList());
        l.sort((a, b) -> {
            int abits = getBits(a);
            int bbits = getBits(b);
            return abits - bbits == 0 ? a - b : abits - bbits;
        });
        int[] res = new int[arr.length];
        for (int i = 0; i < res.length; i++) res[i] = l.get(i);
        return res;
    }

    private static int getBits(int x) {
        int c = 0;
        for (int i = 0; i < 31; i++) {
            if (((x >> i) & 1) == 1) c++;
        }
        return c;
    }
}
