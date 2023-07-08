package arrays.PairsofSongsWithTotalDurationsDivisibleby60;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        int[] times = {30,20,150,100,40};
        int[] times2 = {60,60,60};
        int[] times3 = {418,204,77,278,239,457,284,263,372,279,476,416,360,18};
        System.out.println(numPairsDivisibleBy60(times));
        System.out.println(numPairsDivisibleBy60(times2));
        System.out.println(numPairsDivisibleBy60(times3));
    }

    public static int numPairsDivisibleBy60(int[] time) {
        int remainders[] = new int[60];
        int count = 0;
        for (int t: time) {
            if (t % 60 == 0) { // check if a%60==0 && b%60==0
                count += remainders[0];
            } else { // check if a%60+b%60==60
                count += remainders[60 - t % 60];
            }
            remainders[t % 60]++; // remember to update the remainders
        }
        return count;
    }
}
