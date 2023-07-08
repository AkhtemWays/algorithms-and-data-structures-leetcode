package arrays.OriginalArrayFromDoubledArray;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }

    public int[] findOriginalArray(int[] changed) {
        int[] empty = {};
        if (changed.length % 2 != 0) return empty;
        int[] counts = new int[100001];
        for (int val : changed) counts[val]++;
        int[] res = new int[changed.length / 2];
        Arrays.sort(changed);
        int k = 0;
        if (counts[0] % 2 != 0) return empty;
        for (int i = changed.length - 1; i >= 0; i--) {
            int val = changed[i];
            if (counts[val] > 0) {
                if (k == res.length) return empty;
                counts[val]--;
                res[k++] = val / 2;
                counts[val]--;
                counts[val/2]--;
            }
        }
        return k == res.length ? res : empty;
    }
}
