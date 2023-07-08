package strings.OrderlyQueue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println(orderlyQueue("cba", 1));
        System.out.println(orderlyQueue("baaca", 3));
        System.out.println(orderlyQueue("nhtq", 1));
    }

    public static String orderlyQueue(String s, int k) {
        int[] counts = new int[26];
        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (byte i = 0; i < s.length(); i++) counts[s.charAt(i) - 'a']++;

        int[] takenCounts = new int[26];
        int l = k;
        for (byte i = 0; i < 26; i++) {
            takenCounts[i] = Math.min(l, counts[i]);
            l -= Math.min(l, counts[i]);
            if (l == 0) break;
        }
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < k; i++) {
            for (int m = 0; m < k; m++) {
                char ch = sb.charAt(m);
                sb.deleteCharAt(m);
                sb.append(ch);
            }
        }
        return sb.toString();
    }
    
    private static int getNext(int[] takenCounts, int i) {
        if (takenCounts[i] > 0) {
            takenCounts[i]--;
            return i;
        }
        for (int k = i - 1; k >= 0; k--) {
            if (takenCounts[k] > 0) {
                takenCounts[k]--;
                return k;
            }
        }
        return -1;
    }
}
