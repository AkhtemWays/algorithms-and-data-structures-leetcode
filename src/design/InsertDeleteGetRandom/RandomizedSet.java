package design.InsertDeleteGetRandom;

import java.util.*;

public class RandomizedSet {
    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.insert(1)); // true
        System.out.println(randomizedSet.remove(2)); // false
        System.out.println(randomizedSet.insert(2)); // true
        System.out.println(randomizedSet.getRandom()); // 1 or 2
        System.out.println(randomizedSet.remove(1)); // true, values = [2]
        System.out.println(randomizedSet.insert(2)); // false
        System.out.println(randomizedSet.getRandom()); // 2
    }
    Random random = new Random();
    private final List<Integer> values;
    private final HashMap<Integer, Integer> valuesToIndices;
    public RandomizedSet() {
        this.values = new ArrayList<>();
        this.valuesToIndices = new HashMap<>();
    }

    public boolean insert(int val) {
        if (valuesToIndices.containsKey(val)) {
            return false;
        } else {
            values.add(val);
            valuesToIndices.put(val, values.size() - 1);
            return true;
        }
    }

    public boolean remove(int val) {
        if (valuesToIndices.containsKey(val)) {
            int index = valuesToIndices.get(val);
            if (values.size() - 1 == index) {
                values.remove(index);
                valuesToIndices.remove(val);
            } else {
                Collections.swap(values, index, values.size() - 1);
                values.remove(values.size() - 1);
                valuesToIndices.remove(val);
                valuesToIndices.put(values.get(index), index);
            }
            return true;
        }
        return false;
    }

    public int getRandom() {
        return values.get(random.nextInt(values.size()));
    }
}
