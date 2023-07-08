package design.TwoSumIIIDatastructuredesign;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TwoSum {

    private Set<Integer> sums;
    private ArrayList<Integer> list;
    public TwoSum() {
        this.sums = new HashSet<>();
        this.list = new ArrayList<>();
    }

    public void add(int number) {
        list.add(number);
        for (int i = 0; i < list.size() - 1; i++) {
            sums.add(number + list.get(i));
        }
    }

    public boolean find(int value) {
        return sums.contains(value);
    }
}