package tictactoe.players;

import tictactoe.Cell;
import tictactoe.Game;
import tictactoe.TargetCoordinates;

public class EasyAI implements Player {

    @Override
    public TargetCoordinates makeCoordinates(Cell[][] grid) {
        int randomRow = Game.random.nextInt(Game.GRID_SIZE);
        int randomColumn = Game.random.nextInt(Game.GRID_SIZE);

        while (grid[randomRow][randomColumn] != Cell.EMPTY) {
            randomRow = Game.random.nextInt(Game.GRID_SIZE);
            randomColumn = Game.random.nextInt(Game.GRID_SIZE);
        }

        return new TargetCoordinates(randomRow, randomColumn);
    }
}
