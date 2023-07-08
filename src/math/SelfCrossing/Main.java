package math.SelfCrossing;

public class Main {
    public static void main(String[] args) {

    }

    public boolean isSelfCrossing(int[] distance) {
        int l = distance.length;
        if (l <= 3) return false;

        for (int i = 3; i < l; i++) {
            if (distance[i-3] - distance[i-1] >= 0 && distance[i] - distance[i-2] >= 0) return true;
            if (i-4 >= 0) {
                if (distance[i] + distance[i-4] >= distance[i-2] && distance[i-1] == distance[i-3]) return true;
                if (distance[i] + distance[i-4] >= distance[i-2] && distance[i-1] <= distance[i-3]) return true;
            }
        }
        return false;
    }
}
