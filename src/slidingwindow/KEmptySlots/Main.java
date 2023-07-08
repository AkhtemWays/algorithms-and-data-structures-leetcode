package slidingwindow.KEmptySlots;

import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int[] bulbs = {3,9,2,8,1,6,10,5,4,7};
        System.out.println(kEmptySlots(bulbs, 1));
    }

    public static int kEmptySlots(int[] bulbs, int K) {
        int n=bulbs.length;
        int[] position =new int[n+1];
        for(int i=0;i<n;i++) position[bulbs[i]]=i+1;
        int result=Integer.MAX_VALUE;
        int start=1,end=start+K+1;
        for(int i=1; end<=n; i++) {
            if(position[i]>position[start] && position[i]>position[end]) continue;
            if(i==end) result=Math.min(result,Math.max(position[start],position[end]));
            start=i;
            end=start+K+1;
        }
        return result==Integer.MAX_VALUE?-1:result;
    }
}
