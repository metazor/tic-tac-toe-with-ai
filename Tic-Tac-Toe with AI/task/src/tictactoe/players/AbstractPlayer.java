package tictactoe.players;

import tictactoe.Cell;
import tictactoe.Table;
import tictactoe.TargetCoordinates;

abstract class AbstractPlayer {

    Cell[][] grid = new Cell[Table.GRID_SIZE][Table.GRID_SIZE];

    AbstractPlayer(Cell[][] grid) {
        for (int i = 0; i < Table.GRID_SIZE; i++) {
            for (int j = 0; j < Table.GRID_SIZE; j++) {
                this.grid[i][j] = Cell.valueOf(grid[i][j].toString());
            }
        }
    }

    public abstract TargetCoordinates makeCoordinates();
}
