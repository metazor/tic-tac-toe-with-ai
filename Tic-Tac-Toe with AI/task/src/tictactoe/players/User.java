package tictactoe.players;

import tictactoe.Cell;
import tictactoe.TargetCoordinates;
import tictactoe.UI;

public class User extends AbstractPlayer {

    public User(Cell[][] grid) {
        super(grid);
    }

    @Override
    public TargetCoordinates makeCoordinates() {
        return UI.readCoordinates(grid);
    }
}
