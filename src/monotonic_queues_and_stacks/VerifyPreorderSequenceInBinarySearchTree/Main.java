package monotonic_queues_and_stacks.VerifyPreorderSequenceInBinarySearchTree;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int[] preorder1 = {5,2,1,3,6};
        int[] preorder2 = {5,2,6,1,3};
        int[] preorder3 = {2,3,1};
        int[] preorder4 = {1,2};
        int[] preorder5 = {2,1};
        System.out.println(main.verifyPreorder(preorder1)); // true
        System.out.println(main.verifyPreorder(preorder2)); // false
        System.out.println(main.verifyPreorder(preorder3)); // false
        System.out.println(main.verifyPreorder(preorder4)); // true
        System.out.println(main.verifyPreorder(preorder5)); // true
    }

    public boolean verifyPreorder(int[] preorder) {
        Stack<Integer> stack = new Stack<>();
        int low = Integer.MIN_VALUE;
        for (int val : preorder) {
            if (low > val) return false;
            while (!stack.isEmpty() && stack.peek() < val) {
                low = stack.pop();
            }
            stack.add(val);
        }
        return true;
    }
}
