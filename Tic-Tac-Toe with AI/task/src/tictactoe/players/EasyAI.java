package tictactoe.players;

import tictactoe.Cell;
import tictactoe.Table;
import tictactoe.TargetCoordinates;

import java.util.Random;
import java.util.random.RandomGenerator;

public class EasyAI extends AbstractPlayer {

    private final RandomGenerator random = new Random();

    public EasyAI(Cell[][] grid) {
        super(grid);
    }

    @Override
    public TargetCoordinates makeCoordinates() {
        int randomRow = random.nextInt(Table.GRID_SIZE);
        int randomColumn = random.nextInt(Table.GRID_SIZE);

        while (grid[randomRow][randomColumn] != Cell.EMPTY) {
            randomRow = random.nextInt(Table.GRID_SIZE);
            randomColumn = random.nextInt(Table.GRID_SIZE);
        }

        TargetCoordinates targetCoordinates = new TargetCoordinates();
        targetCoordinates.setRow(randomRow);
        targetCoordinates.setColumn(randomColumn);
        return targetCoordinates;
    }
}
