package tictactoe.players;

import tictactoe.Cell;
import tictactoe.TargetCoordinates;
import tictactoe.UI;

public class User implements Player {

    private final UI ui;

    public User(UI ui) {
        this.ui = ui;
    }

    @Override
    public TargetCoordinates makeCoordinates(Cell[][] grid) {
        return ui.readCoordinates(grid);
    }
}
