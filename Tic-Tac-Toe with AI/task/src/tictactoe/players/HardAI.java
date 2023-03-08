package tictactoe.players;

import tictactoe.Cell;
import tictactoe.Minimax;
import tictactoe.TargetCoordinates;

public class HardAI extends AbstractPlayer {

    private final boolean currentRoundIsX;

    public HardAI(Cell[][] grid, boolean currentRoundIsX) {
        super(grid);
        this.currentRoundIsX = currentRoundIsX;
    }

    @Override
    public TargetCoordinates makeCoordinates() {
        TargetCoordinates targetCoordinates = new TargetCoordinates();
        Minimax minimax = new Minimax(grid);

        if (currentRoundIsX) {
            minimax.findBestMove(Cell.CROSS);
        } else {
            minimax.findBestMove(Cell.ZERO);
        }

        targetCoordinates.setRow(minimax.getBestRow());
        targetCoordinates.setColumn(minimax.getBestColumn());
        return targetCoordinates;
    }
}
