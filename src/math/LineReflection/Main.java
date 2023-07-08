package math.LineReflection;

import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        int[][] points = {{1,1},{-1,1}};
        int[][] points2 = {{1,1},{-1,-1}};
        int[][] points3 = {{-16,1},{16,1},{16,1}};
        int[][] points4 = {{3, 1},{1,1},{0,0},{-1,1}, {-3, 1}};
//        System.out.println(isReflected(points));
//        System.out.println(isReflected(points2));
//        System.out.println(isReflected(points3));
        System.out.println(isReflected(points4));
    }

    public static boolean isReflected(int[][] points) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        HashSet<String> set = new HashSet<>();
        for(int[] p:points){
            max = Math.max(max,p[0]);
            min = Math.min(min,p[0]);
            String str = p[0] + "a" + p[1];
            set.add(str);
        }
        int mid = max+min;
        for(int[] p:points){
            //int[] arr = {sum-p[0],p[1]};
            String str = (mid-p[0]) + "a" + p[1];
            if( !set.contains(str))
                return false;

        }
        return true;
    }
}
