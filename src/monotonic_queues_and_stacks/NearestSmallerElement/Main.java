package monotonic_queues_and_stacks.NearestSmallerElement;

import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

    }

    public ArrayList<Integer> prevSmaller(ArrayList<Integer> A) {
        ArrayList<Integer> res = new ArrayList<>();
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < A.size(); i++) {
            while (!st.isEmpty() && st.peek() >= A.get(i)) {
                st.pop();
            }
            if (st.isEmpty()) {
                res.add(-1);
            } else {
                res.add(st.peek());
            }
            st.add(A.get(i));
        }
        return res;
    }
}
