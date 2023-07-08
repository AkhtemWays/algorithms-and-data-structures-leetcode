package math.battleField;

public class BattleField {
    private final int[][] cell;
    private boolean[][] checkedCells;
    private int submarines;
    private int destroyers;
    private int cruiserShips;
    private int battleShips;

    /* Main kata method */
    public static boolean fieldValidator(int[][] field) {
        BattleField bf = new BattleField(field);

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (!bf.checkedCells[i][j]) {
                    if (bf.cell[i][j] == 1 && !bf.checkPart(i, j)) {
                        return false;
                    } else {
                        bf.checkedCells[i][j] = true;
                    }
                }
            }
        }

        return bf.submarines == 0 && bf.cruiserShips == 0 && bf.destroyers == 0 && bf.battleShips == 0;
    }

    public BattleField(int[][] cell) {
        this.cell = cell;
        submarines = 4;
        destroyers = 3;
        cruiserShips = 2;
        battleShips = 1;
        checkedCells = new boolean[cell.length][cell[0].length];
    }

    private boolean checkPart(int y, int x) {
        System.out.println(String.format("Ship part found at %d %d", y, x));

        final int neighbors = countNeighbors(y, x);

        if (neighbors > 2 || (neighbors == 1 && !findShip(y, x))) {
            return false;
        } else if (neighbors == 0) {
            submarines--;
            checkedCells[y][x] = true;
            System.out.println(String.format("\t- submarine count: %d", submarines));
        }

        return true;
    }

    private int countNeighbors(int y, int x) {
        int neighbors = 0;

        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                if (!(i == y && j == x) && isValidCoodrinates(i, j)) {
                    if (cell[i][j] == 1) {
                        neighbors++;
                    } else {
                        checkedCells[i][j] = true;
                    }
                }
            }
        }
        return neighbors;
    }

    private boolean isValidCoodrinates(int y, int x) {
        return y >= 0 &&
                x >= 0 &&
                y < cell.length &&
                x < cell[y].length;
    }

    private boolean findShip(int y, int x) {
        int size = 1;

        int dX = 0, dY = 0, currentY = -1, currentX = -1;

        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                if (isValidCoodrinates(i, j)) {
                    if (i == y && j == x) {
                        continue;
                    } else if (cell[i][j] == 1) {
                        if (isDiagonalNeighborPresent(i, j)) return false;
                        dY = i - y;
                        dX = j - x;
                        currentY = i + dY;
                        currentX = j + dX;
                        size++;
                        break;
                    } else {
                        checkedCells[i][j] = true;
                    }
                }
            }
        }

        if (currentX != -1) {
            for (; currentX >= 0 &&
                    currentX < cell[0].length &&
                    currentY >= 0 &&
                    currentY < cell.length; currentX += dX, currentY += dY) {
                if (cell[currentY][currentX] == 1) {
                    if (isDiagonalNeighborPresent(currentY, currentX)) {
                        return false;
                    }
                    size++;
                } else {
                    if (size > 4) {
                        return false;
                    }
                    break;
                }
            }
        }

        removeShipBySize(currentY - dY, currentX - dX, dY, dX, size);

        return submarines >= 0 && cruiserShips >= 0 && destroyers >= 0 && battleShips >= 0;
    }

    private boolean isDiagonalNeighborPresent(int y, int x) {
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                if (i != y && j != x && isValidCoodrinates(i, j)) {
                    if (cell[i][j] == 1) {
                        return true;
                    } else {
                        checkedCells[i][j] = true;
                    }
                }
            }
        }
        return false;
    }

    private void removeShipBySize(int currentY, int currentX, int dY, int dX, int size) {
        int y = currentY;
        int x = currentX;
        int sizeCopy = size;


        if (sizeCopy == 2) {
            destroyers--;
        } else if (sizeCopy == 3) {
            cruiserShips--;
        } else if (sizeCopy == 4) {
            battleShips--;
        }

        while (size != 0) {
            checkedCells[currentY][x] = true;
            x -= dX;
            y -= dY;
            sizeCopy--;
        }
    }
}
