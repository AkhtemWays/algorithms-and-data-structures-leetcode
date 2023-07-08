package trees.FenwickTree;

import java.util.Arrays;

public class FenwickTree {
    int[] bit;
    int[] nums;
    FenwickTree(int[] input) {
        nums = Arrays.copyOf(input, input.length);
        createTree(input);
    }

    private void update(int val, int index) {
        nums[index] += val;
        while(index < bit.length){
            bit[index] += val;
            index = getNext(index);
        }
    }
    public void updateInternal(int val, int index){
        while(index < bit.length){
            bit[index] += val;
            index = getNext(index);
        }
    }

    public void update(int val, int l, int r) {
        update(val, l);
        update(-val, r+1);
    }

    public int sum(int index){
        index = index + 1;
        int sum = 0;
        while(index > 0){
            sum += bit[index];
            index = getParent(index);
        }
        return sum;
    }

    public int sum(int l, int r) {
        return sum(r) - sum(l-1);
    }
    public void createTree(int[] input) {
        bit = new int[input.length+1];
        for(int i=1; i <= input.length; i++) {
            updateInternal(input[i-1], i);
        }
    }
    private int getParent(int index){
        return index - (index & -index);
    }
    private int getNext(int index){
        return index + (index & -index);
    }

    public static void main(String[] args){
        int[] input = {3, 2, -1, 6, 5, 4, -3, 3, 7, 2, 3};
        FenwickTree ft = new FenwickTree(input);
//        ft.update(4, 1);
//        ft.update(4, 2);
//        ft.update(4, 5);
        ft.update(2, 1, 4);
        System.out.println(ft.sum(4)); // 0-4 23
        System.out.println(ft.sum(2, 4)); // 14
        System.out.println(ft.sum(2, 8));
    }
}
