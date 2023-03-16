package tictactoe.players;

import tictactoe.Cell;
import tictactoe.CheckTable;
import tictactoe.TargetCoordinates;

public class MediumAI extends AbstractPlayer {

    private final boolean currentRoundIsX;
    private final CheckTable checkTable;

    public MediumAI(Cell[][] grid, boolean currentRoundIsX) {
        super(grid);
        this.currentRoundIsX = currentRoundIsX;
        checkTable = new CheckTable(grid);
    }

    @Override
    public TargetCoordinates makeCoordinates() {
        TargetCoordinates targetCoordinates;

        if (currentRoundIsX) {
            if (checkTable.has2X()) {
                targetCoordinates = setTargetCoordinates(Cell.CROSS);
            } else if (checkTable.has2O()) {
                targetCoordinates = setTargetCoordinates(Cell.ZERO);
            } else {
                targetCoordinates = new EasyAI(grid).makeCoordinates();
            }
        } else {
            if (checkTable.has2O()) {
                targetCoordinates = setTargetCoordinates(Cell.ZERO);
            } else if (checkTable.has2X()) {
                targetCoordinates = setTargetCoordinates(Cell.CROSS);
            } else {
                targetCoordinates = new EasyAI(grid).makeCoordinates();
            }
        }

        return targetCoordinates;
    }

    private TargetCoordinates setTargetCoordinates(Cell labelToSet) {
        if (labelToSet == Cell.CROSS) {
            return new TargetCoordinates(checkTable.getSequenceRowX(), checkTable.getSequenceColumnX());
        } else {
            return new TargetCoordinates(checkTable.getSequenceRowO(), checkTable.getSequenceColumnO());
        }
    }
}
