package design.Flatten2DVector;

import java.util.ArrayList;
import java.util.List;

public class Vector2D {
    public static void main(String[] args) {
        int[][] vec = {{1, 2}, {3}, {4}};
        Vector2D vector2D = new Vector2D(vec);
        System.out.println(vector2D.next());    // return 1
        System.out.println(vector2D.next());    // return 2
        System.out.println(vector2D.next());    // return 3
        System.out.println(vector2D.hasNext()); // return True
        System.out.println(vector2D.hasNext()); // return True
        System.out.println(vector2D.next());    // return 4
        System.out.println(vector2D.hasNext()); // return False
    }
    private int iterator;
    private final List<Integer> flattenedVector = new ArrayList<>();
    public Vector2D(int[][] vec) {
        for (int i = 0; i < vec.length; i++) {
            for (int j = 0; j < vec[i].length; j++) {
                flattenedVector.add(vec[i][j]);
            }
        }
        iterator = -1;
    }

    public int next() {
        return flattenedVector.get(++iterator);
    }

    public boolean hasNext() {
        return iterator + 1 < flattenedVector.size();
    }
}
