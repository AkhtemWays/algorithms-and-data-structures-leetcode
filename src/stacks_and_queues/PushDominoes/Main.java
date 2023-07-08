package stacks_and_queues.PushDominoes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println(pushDominoes(".L.R...LR..L..")); // LL.RR.LLRRLL..
        System.out.println(pushDominoes("RR.L")); // RR.L
    }

    public static String pushDominoes(String dominoes) {
        List<Integer> Lpositions = new ArrayList<>();
        List<Integer> Rpositions = new ArrayList<>();
        for (int i = 0; i < dominoes.length(); i++) {
            if (dominoes.charAt(i) == 'L') Lpositions.add(i);
            else if (dominoes.charAt(i) == 'R') Rpositions.add(i);
        }
        Set<Integer> processedPositions = new HashSet<>();

        char[] result = dominoes.toCharArray();

        int l = 0, r = 0;
        while (l < Lpositions.size() && r < Rpositions.size()) {
            int Lposition = Lpositions.get(l);
            int Rposition = Rpositions.get(r);
            if (processedPositions.contains(Lposition)) {
                l++;
                continue;
            }
            else if (processedPositions.contains(Rposition)) {
                r++;
                continue;
            }
            if (r+1 < Rpositions.size() && Rpositions.get(r+1) < Lposition) {
                int end = Rpositions.get(r+1);
                for (int i = Rposition; i < end; i++) {
                    result[i] = 'R';
                    processedPositions.add(i);
                }
                r++;
            }
            else if (Lposition < Rposition) {
                int start = r == 0 ? -1 : Rpositions.get(r-1);
                for (int i = Lposition-1; i > start; i--) {
                    result[i] = 'L';
                    processedPositions.add(i);
                }
                l++;
            } else {
                int i = Lposition, j = Rposition;
                while (--i > ++j) {
                    result[i] = 'L';
                    result[j] = 'R';
                    processedPositions.add(i);
                    processedPositions.add(j);
                }
                if (i == j) {
                    while (Lposition < result.length && Rposition >= 0 && result[Lposition] == 'L' && result[Rposition] == 'R') {
                        Lposition++;
                        --Rposition;
                    }
                    if (Rposition >= 0 && result[Rposition] == 'R' && Lposition < result.length && result[Lposition] != 'L') {
                        result[i] = 'R';
                    } else if (Lposition < result.length && result[Lposition] == 'L' && Rposition >= 0 && result[Rposition] != 'R') {
                        result[i] = 'L';
                    }
                }
                l++;
                r++;
            }
        }
        while (l < Lpositions.size()) {
            int start = l-1 >= 0 ? Lpositions.get(l-1) : -1;
            for (int i = Lpositions.get(l)-1; i > start; i--) {
                result[i] = 'L';
            }
            l++;
        }
        while (r < Rpositions.size()) {
            int end = r + 1 < Rpositions.size() ? Rpositions.get(r+1) : dominoes.length();
            for (int i = Rpositions.get(r)+1; i < end; i++) {
                result[i] = 'R';
            }
            r++;
        }

        return String.valueOf(result);
    }
}
