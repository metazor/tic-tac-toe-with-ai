package tictactoe.players;

import tictactoe.Cell;
import tictactoe.CheckTable;
import tictactoe.TargetCoordinates;

public class MediumAI implements Player {

    private final boolean currentRoundIsX;

    public MediumAI(boolean currentRoundIsX) {
        this.currentRoundIsX = currentRoundIsX;
    }

    @Override
    public TargetCoordinates makeCoordinates(Cell[][] grid) {
        TargetCoordinates targetCoordinates;
        CheckTable checkTable = new CheckTable(grid);

        if (currentRoundIsX) {
            if (checkTable.has2X()) {
                targetCoordinates = setTargetCoordinates(Cell.CROSS, checkTable);
            } else if (checkTable.has2O()) {
                targetCoordinates = setTargetCoordinates(Cell.ZERO, checkTable);
            } else {
                targetCoordinates = new EasyAI().makeCoordinates(grid);
            }
        } else {
            if (checkTable.has2O()) {
                targetCoordinates = setTargetCoordinates(Cell.ZERO, checkTable);
            } else if (checkTable.has2X()) {
                targetCoordinates = setTargetCoordinates(Cell.CROSS, checkTable);
            } else {
                targetCoordinates = new EasyAI().makeCoordinates(grid);
            }
        }

        return targetCoordinates;
    }

    private TargetCoordinates setTargetCoordinates(Cell labelToSet, CheckTable checkTable) {
        if (labelToSet == Cell.CROSS) {
            return new TargetCoordinates(checkTable.getSequenceRowX(), checkTable.getSequenceColumnX());
        } else {
            return new TargetCoordinates(checkTable.getSequenceRowO(), checkTable.getSequenceColumnO());
        }
    }
}
