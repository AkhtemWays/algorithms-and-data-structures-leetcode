package trees.ConstructQuadTree;

class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;


    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] grid = {{1,1,1,1,0,0,0,0},
                        {1,1,1,1,0,0,0,0},
                        {1,1,1,1,1,1,1,1},
                        {1,1,1,1,1,1,1,1},
                        {1,1,1,1,0,0,0,0},
                        {1,1,1,1,0,0,0,0},
                        {1,1,1,1,0,0,0,0},
                        {1,1,1,1,0,0,0,0}};
        Node node = construct(grid);
        System.out.println(node);
    }

    public static Node construct(int[][] grid) {
        return construct(grid, 0, grid.length-1, 0, grid.length-1);
    }

    private static Node construct(int[][] grid, int leftX, int rightX, int leftY, int rightY) {
        int sum = getSum(grid, leftX, rightX, leftY, rightY);
        int allOnes = (rightX - leftX + 1) * (rightY - leftY + 1);
        if (sum == allOnes) {
            return new Node(true, true);
        }
        else if (sum == 0) {
            return new Node(false, true);
        } else {
            int midX = (rightX + leftX) / 2;
            int midY = (rightY + leftY) / 2;
            return new Node(
                    false,
                    false,
                    construct(grid, leftX, midX, leftY, midY),
                    construct(grid, leftX, midX, midY+1, rightY),
                    construct(grid, midX+1, rightX, leftY, midY),
                    construct(grid, midX+1, rightX, midY+1, rightY));
        }
    }

    private static int getSum(int[][] grid, int leftX, int rightX, int leftY, int rightY) {
        int sum = 0;
        for (int i = leftY; i <= rightY; i++) {
            for (int j = leftX; j <= rightX; j++) {
                sum += grid[i][j];
            }
        }
        return sum;
    }
}
