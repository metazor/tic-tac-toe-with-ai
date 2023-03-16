package tictactoe.players;

import tictactoe.Cell;
import tictactoe.TargetCoordinates;
import tictactoe.UI;

public class User extends AbstractPlayer {

    private final UI ui;

    public User(Cell[][] grid, UI ui) {
        super(grid);
        this.ui = ui;
    }

    @Override
    public TargetCoordinates makeCoordinates() {
        return ui.readCoordinates(grid);
    }
}
