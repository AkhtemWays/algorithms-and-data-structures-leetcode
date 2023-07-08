package arrays.ContainsDuplicate;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> unique = new HashSet<>();
        for (int num : nums) {
            if (unique.contains(num)) return true;
            unique.add(num);
        }
        return false;
    }
}
