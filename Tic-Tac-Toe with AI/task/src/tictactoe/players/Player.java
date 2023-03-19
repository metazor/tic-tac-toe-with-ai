package tictactoe.players;

import tictactoe.Cell;
import tictactoe.TargetCoordinates;

public interface Player {

    TargetCoordinates makeCoordinates(Cell[][] grid);
}
