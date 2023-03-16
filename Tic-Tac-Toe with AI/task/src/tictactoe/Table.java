package tictactoe;

import java.util.Arrays;

public class Table {

    public static final int GRID_SIZE = 3;
    private final Cell[][] grid = new Cell[GRID_SIZE][GRID_SIZE];

    Table() {
        for (Cell[] cells : grid) {
            Arrays.fill(cells, Cell.EMPTY);
        }
    }

    public static boolean hasEmptyCells(Cell[][] grid) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j] == Cell.EMPTY) {
                    return true;
                }
            }
        }

        return false;
    }

    void addMarkToTargetCell(TargetCoordinates targetCoordinates, boolean currentRoundIsX) {
        if (currentRoundIsX) {
            grid[targetCoordinates.getRow()][targetCoordinates.getColumn()] = Cell.CROSS;
        } else {
            grid[targetCoordinates.getRow()][targetCoordinates.getColumn()] = Cell.ZERO;
        }
    }

    public Cell[][] getGrid() {
        Cell[][] gridCopy = new Cell[GRID_SIZE][GRID_SIZE];

        for (int i = 0; i < Table.GRID_SIZE; i++) {
            for (int j = 0; j < Table.GRID_SIZE; j++) {
                gridCopy[i][j] = Cell.valueOf(grid[i][j].toString());
            }
        }

        return gridCopy;
    }
}
