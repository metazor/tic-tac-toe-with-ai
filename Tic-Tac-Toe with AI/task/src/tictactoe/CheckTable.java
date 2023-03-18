package tictactoe;

public class CheckTable {

    private boolean tableHas3X;
    private boolean tableHas3O;
    private boolean tableHas2X;
    private boolean tableHas2O;
    private int sequenceRowX;
    private int sequenceRowO;
    private int sequenceColumnX;
    private int sequenceColumnO;
    private boolean sequenceRowXSet;
    private boolean sequenceRowOSet;

    public CheckTable(Cell[][] grid) {
        checkRows(grid);

        if (!tableHas2X && !tableHas2O) {
            checkColumns(grid);
        }

        if (!tableHas2X && !tableHas2O) {
            checkTopLeftDiagonal(grid);
        }

        if (!tableHas2X && !tableHas2O) {
            checkTopRightDiagonal(grid);
        }
    }

    private void checkRows(Cell[][] grid) {
        for (int i = 0; i < Game.GRID_SIZE; i++) {
            String currentRow = grid[i][0].getLabel() + grid[i][1].getLabel() + grid[i][2].getLabel();
            checkSequences(currentRow);
            int emptyCellCoordinate = currentRow.indexOf(' ');

            if (tableHas2X && !sequenceRowXSet) {
                sequenceRowX = i;
                sequenceRowXSet = true;
                sequenceColumnX = emptyCellCoordinate;
            } else if (tableHas2O && !sequenceRowOSet) {
                sequenceRowO = i;
                sequenceRowOSet = true;
                sequenceColumnO = emptyCellCoordinate;
            }
        }
    }

    private void checkSequences(String sequence) {
        if ("XXX".equals(sequence)) {
            tableHas3X = true;
        } else if ("OOO".equals(sequence)) {
            tableHas3O = true;
        } else if (sequence.matches(" ?X ?X ?")) {
            tableHas2X = true;
        } else if (sequence.matches(" ?O ?O ?")) {
            tableHas2O = true;
        }
    }

    private void checkColumns(Cell[][] grid) {
        for (int i = 0; i < Game.GRID_SIZE; i++) {
            String currentColumn = grid[0][i].getLabel() + grid[1][i].getLabel() + grid[2][i].getLabel();
            checkSequences(currentColumn);
            int emptyCellCoordinate = currentColumn.indexOf(' ');

            if (tableHas2X && !sequenceRowXSet) {
                sequenceRowX = emptyCellCoordinate;
                sequenceRowXSet = true;
                sequenceColumnX = i;
            } else if (tableHas2O && !sequenceRowOSet) {
                sequenceRowO = emptyCellCoordinate;
                sequenceRowOSet = true;
                sequenceColumnO = i;
            }
        }
    }

    private void checkTopLeftDiagonal(Cell[][] grid) {
        String topLeftDiagonal = grid[0][0].getLabel() + grid[1][1].getLabel() + grid[2][2].getLabel();
        checkSequences(topLeftDiagonal);
        int emptyCellCoordinate = topLeftDiagonal.indexOf(' ');

        if (tableHas2X) {
            sequenceRowX = emptyCellCoordinate;
            sequenceColumnX = emptyCellCoordinate;
        } else if (tableHas2O) {
            sequenceRowO = emptyCellCoordinate;
            sequenceColumnO = emptyCellCoordinate;
        }
    }

    private void checkTopRightDiagonal(Cell[][] grid) {
        String topRightDiagonal = grid[0][2].getLabel() + grid[1][1].getLabel() + grid[2][0].getLabel();
        checkSequences(topRightDiagonal);
        int emptyCellCoordinate = topRightDiagonal.indexOf(' ');

        if (tableHas2X) {
            sequenceRowX = emptyCellCoordinate;

            sequenceColumnX = switch (emptyCellCoordinate) {
                case 0 -> 2;
                case 1 -> 1;
                case 2 -> 0;
                default -> throw new IllegalStateException(Game.UNEXPECTED_VALUE + emptyCellCoordinate);
            };
        } else if (tableHas2O) {
            sequenceRowO = emptyCellCoordinate;

            sequenceColumnO = switch (emptyCellCoordinate) {
                case 0 -> 2;
                case 1 -> 1;
                case 2 -> 0;
                default -> throw new IllegalStateException(Game.UNEXPECTED_VALUE + emptyCellCoordinate);
            };
        }
    }

    public boolean has3X() {
        return tableHas3X;
    }

    public boolean has3O() {
        return tableHas3O;
    }

    public boolean has2X() {
        return tableHas2X;
    }

    public boolean has2O() {
        return tableHas2O;
    }

    public int getSequenceRowX() {
        return sequenceRowX;
    }

    public int getSequenceRowO() {
        return sequenceRowO;
    }

    public int getSequenceColumnX() {
        return sequenceColumnX;
    }

    public int getSequenceColumnO() {
        return sequenceColumnO;
    }
}
