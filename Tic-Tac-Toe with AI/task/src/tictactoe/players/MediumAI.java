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
            if (checkTable.isTableHas2X()) {
                targetCoordinates = setTargetCoordinates(Cell.CROSS);
            } else if (checkTable.isTableHas2O()) {
                targetCoordinates = setTargetCoordinates(Cell.ZERO);
            } else {
                targetCoordinates = new EasyAI(grid).makeCoordinates();
            }
        } else {
            if (checkTable.isTableHas2O()) {
                targetCoordinates = setTargetCoordinates(Cell.ZERO);
            } else if (checkTable.isTableHas2X()) {
                targetCoordinates = setTargetCoordinates(Cell.CROSS);
            } else {
                targetCoordinates = new EasyAI(grid).makeCoordinates();
            }
        }

        return targetCoordinates;
    }

  private TargetCoordinates setTargetCoordinates(Cell labelToSet) {
        TargetCoordinates targetCoordinates = new TargetCoordinates();

        if (labelToSet == Cell.CROSS) {
            targetCoordinates.setRow(checkTable.getSequenceRowX());
            targetCoordinates.setColumn(checkTable.getSequenceColumnX());
        } else {
            targetCoordinates.setRow(checkTable.getSequenceRowO());
            targetCoordinates.setColumn(checkTable.getSequenceColumnO());
        }

        return targetCoordinates;
    }
}
