package design.InsertDeleteGetRandomDuplicates;

import java.util.*;

public class RandomizedCollection {

    private static void test1() {
        RandomizedCollection randomizedCollection = new RandomizedCollection();
        System.out.println(randomizedCollection.insert(1));   // return true since the collection does not contain 1.
        // Inserts 1 into the collection.
        System.out.println(randomizedCollection.insert(1));   // return false since the collection contains 1.
        // Inserts another 1 into the collection. Collection now contains [1,1].
        System.out.println(randomizedCollection.insert(2));   // return true since the collection does not contain 2.
        // Inserts 2 into the collection. Collection now contains [1,1,2].
        System.out.println(randomizedCollection.getRandom()); // getRandom should:
        // - return 1 with probability 2/3, or
        // - return 2 with probability 1/3.
        System.out.println(randomizedCollection.remove(1));   // return true since the collection contains 1.
        // Removes 1 from the collection. Collection now contains [1,2].
        System.out.println(randomizedCollection.getRandom()); // getRandom should return 1 or 2, both equally likely.
    }

    private static void test2() {
        RandomizedCollection randomizedCollection = new RandomizedCollection();
        System.out.println(randomizedCollection.insert(1)); // true
        System.out.println(randomizedCollection.remove(1)); // true
        System.out.println(randomizedCollection.insert(1)); // true
    }

    public static void main(String[] args) {
        test2();
    }

    Random random = new Random();
    private final List<Integer> values;
    private final HashMap<Integer, List<Integer>> valuesToIndices;

    public RandomizedCollection() {
        this.values = new ArrayList<>();
        this.valuesToIndices = new HashMap<>();
    }

    public boolean insert(int val) {
        boolean response = !(valuesToIndices.containsKey(val) && valuesToIndices.get(val).size() > 0);
        values.add(val);
        List<Integer> indices = valuesToIndices.getOrDefault(val, new ArrayList<>());
        indices.add(values.size() - 1);
        valuesToIndices.put(val, indices);
        return response;
    }

    public boolean remove(int val) {
        if (valuesToIndices.containsKey(val) && valuesToIndices.get(val).size() > 0) {
            List<Integer> indices = valuesToIndices.get(val);
            int index = indices.remove(indices.size() - 1);
            if (values.size() - 1 == index) {
                values.remove(index);
            } else {
                Collections.swap(values, index, values.size() - 1);
                values.remove(values.size() - 1);
                valuesToIndices.get(values.get(index)).add(index);
            }
            return true;
        }
        return false;
    }

    public int getRandom() {
        return values.get(random.nextInt(values.size()));
    }

}
