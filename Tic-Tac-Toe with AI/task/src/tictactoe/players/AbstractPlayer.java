package tictactoe.players;

import tictactoe.Cell;
import tictactoe.TargetCoordinates;

abstract class AbstractPlayer {

    Cell[][] grid;

    AbstractPlayer(Cell[][] grid) {
        this.grid = grid.clone();
    }

    public abstract TargetCoordinates makeCoordinates();
}
