package tictactoe.players;

import tictactoe.Cell;
import tictactoe.TargetCoordinates;
import tictactoe.UI;

public class User implements Player {

    private final UI ui;
    private final Cell[][] grid;

    public User(Cell[][] grid, UI ui) {
        this.grid = grid;
        this.ui = ui;
    }

    @Override
    public TargetCoordinates makeCoordinates() {
        return ui.readCoordinates(grid);
    }
}
