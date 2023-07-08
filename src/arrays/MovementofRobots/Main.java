package arrays.MovementofRobots;

public class Main {
    private static final int CONSTANT = 1_000_000_000;
    private static void test1() {
        int[] positions = {-2,0,2};
        System.out.println(sumDistance(positions, "RLL", 3));
    }
    private static void test2() {
        int[] positions = {1,0};
        System.out.println(sumDistance(positions, "RL", 2));
    }
    public static void main(String[] args) {
        test1();
        test2();
    }

    private static class State {
        private char direction;
        private int position;
        private int distance;
        State(char direction, int position, int distance) {
            this.direction = direction;
            this.position = position;
            this.distance = distance;
        }
    }
    public static int sumDistance(int[] nums, String s, int d) {
        int n = nums.length;
        State[] states = new State[n+2];
        for (int i = 0; i < n; i++) states[i+1] = new State(s.charAt(i), nums[i], d);
        states[0] = new State('L', -CONSTANT, 0);
        states[n+1] = new State('R', CONSTANT, 0);
        dfs(1, states);
        return states[n].position - states[1].position;
    }

    private static void dfs(int i, State[] states) {
        if (i == 0 || i == states.length-1 || states[i].distance == 0) return;

        State state = states[i];
        if (state.direction == 'R') {
            State nextState = states[i+1];
            if (nextState.direction == 'L') {
                collide(i, i+1, states);
                dfs(i, states);
                dfs(i+1, states);
            } else {
                dfs(i+1, states);
                collide(i, i+1, states);
                dfs(i, states);
                dfs(i+1, states);
            }
        } else {
            State prevState = states[i-1];
            if (prevState.direction == 'R') {
                collide(i, i-1, states);
                dfs(i, states);
                dfs(i-1, states);
            } else {
                dfs(i-1, states);
                collide(i, i-1, states);
                dfs(i, states);
                dfs(i-1, states);
            }
        }
    }

    private static void collide(int i, int j, State[] states) {
        State nextState = states[j];
        State state = states[i];
        int collisionDistance = Math.abs(state.position - nextState.position);
        if (collisionDistance % 2 == 1) {
            collisionDistance = collisionDistance / 2 + 1;
        } else {
            collisionDistance /= 2;
        }
        if (nextState.distance >= collisionDistance && state.distance >= collisionDistance) {
            nextState.distance -= collisionDistance;
            state.distance -= collisionDistance;
            if (state.direction == 'R') {
                state.position += collisionDistance;
                nextState.position -= collisionDistance;
            } else {
                state.position -= collisionDistance;
                nextState.position += collisionDistance;
            }
            state.direction = reverseDirection(state);
            nextState.direction = reverseDirection(state);
        } else if (nextState.distance + state.distance < collisionDistance) {
            if (state.direction == 'R') {
                state.position += state.distance;
                nextState.position -= nextState.distance;
            } else {
                state.position -= state.distance;
                nextState.position += nextState.distance;
            }
            state.distance = 0;
            nextState.distance = 0;
            state.direction = reverseDirection(state);
            nextState.direction = j == 0 || j == states.length-1 ? nextState.direction : reverseDirection(nextState);
        } else if (nextState.distance < collisionDistance) {
            if (state.direction == 'R') {
                state.position += collisionDistance - nextState.distance;
                nextState.position -= nextState.distance;
            } else {
                state.position -= collisionDistance - nextState.distance;
                nextState.position += nextState.distance;
            }
            state.distance -= collisionDistance - nextState.distance;
            nextState.distance = 0;
            state.direction = reverseDirection(state);
            nextState.direction = reverseDirection(state);
        } else {
            if (state.direction == 'R') {
                state.position += state.distance;
                nextState.position -= collisionDistance - state.distance;
            } else {
                state.position -= state.distance;
                nextState.position += collisionDistance - state.distance;
            }
            nextState.distance -= collisionDistance - state.distance;
            state.distance = 0;
            state.direction = reverseDirection(state);
            nextState.direction = reverseDirection(state);
        }
    }

    private static char reverseDirection(State state) {
        if (state.direction == 'R') return 'L';
        return 'R';
    }
}
