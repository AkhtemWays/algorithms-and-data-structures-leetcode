package arrays.RelativeSortArray;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2,1,4,3,9,6};
        System.out.println(Arrays.toString(relativeSortArray(arr1, arr2)));

        int[] arr12 = {28,6,22,8,44,17};
        int[] arr22 = {22,28,8,6};
        System.out.println(Arrays.toString(relativeSortArray(arr12, arr22)));
    }

    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        Set<Integer> unique = new HashSet<>();
        for (int num : arr2) unique.add(num);

        for (int i = arr1.length-1, k = i; i >= 0; i--) {
            int num = arr1[i];
            if (!unique.contains(num)) {
                swap(arr1, i, k--);
            }

            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        int j = 0;
        for (int i = 0; i < arr2.length; i++) {
            int num = arr2[i];
            for (int k = 0; k < counts.get(num); k++, j++) {
                arr1[j] = num;
            }
        }
        Arrays.sort(arr1, j, arr1.length);
        return arr1;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
