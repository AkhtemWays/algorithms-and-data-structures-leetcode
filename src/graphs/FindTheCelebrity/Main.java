package graphs.FindTheCelebrity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

    }

    private boolean knows(int a, int b) {
        return true;
    }

    public int findCelebrity(int n) {
        int celebrity = 0;
        for (int i = 1; i < n; i++) {
            if (knows(celebrity, i)) {
                celebrity = i;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i != celebrity && !knows(i, celebrity)) return -1;
        }
        return celebrity;
    }
}
