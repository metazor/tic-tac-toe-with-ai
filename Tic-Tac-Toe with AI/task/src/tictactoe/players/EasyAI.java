package tictactoe.players;

import tictactoe.Cell;
import tictactoe.Game;
import tictactoe.TargetCoordinates;

import java.util.Random;
import java.util.random.RandomGenerator;

public class EasyAI implements Player {

    private final RandomGenerator random = new Random();
    private final Cell[][] grid;

    public EasyAI(Cell[][] grid) {
        this.grid = grid;
    }

    @Override
    public TargetCoordinates makeCoordinates() {
        int randomRow = random.nextInt(Game.GRID_SIZE);
        int randomColumn = random.nextInt(Game.GRID_SIZE);

        while (grid[randomRow][randomColumn] != Cell.EMPTY) {
            randomRow = random.nextInt(Game.GRID_SIZE);
            randomColumn = random.nextInt(Game.GRID_SIZE);
        }

        return new TargetCoordinates(randomRow, randomColumn);
    }
}
