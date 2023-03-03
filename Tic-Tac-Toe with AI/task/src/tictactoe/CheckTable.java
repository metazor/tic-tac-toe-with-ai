package tictactoe;

class CheckTable {

    int sequenceRowX;
    int sequenceRowO;
    int sequenceColumnX;
    int sequenceColumnO;
    boolean tableHas2X;
    boolean tableHas2O;
    boolean tableHas3X;
    boolean tableHas3O;
    private boolean sequenceRowXSet;
    private boolean sequenceRowOSet;

    void checkLines(Cell[][] field) {
        resetSequenceCoordinates();
        checkRows(field);

        if (!tableHas2X && !tableHas2O) {
            checkColumns(field);
        }

        if (!tableHas2X && !tableHas2O) {
            checkTopLeftDiagonal(field);
        }

        if (!tableHas2X && !tableHas2O) {
            checkTopRightDiagonal(field);
        }
    }

    private void resetSequenceCoordinates() {
        tableHas2X = false;
        tableHas2O = false;
        tableHas3X = false;
        tableHas3O = false;
        sequenceRowXSet = false;
        sequenceRowOSet = false;
    }

    private void checkRows(Cell[][] field) {
        for (int i = 0; i < Table.FIELD_SIZE; i++) {
            String currentRow = field[i][0].label + field[i][1].label + field[i][2].label;
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

    private void checkColumns(Cell[][] field) {
        for (int i = 0; i < Table.FIELD_SIZE; i++) {
            String currentColumn = field[0][i].label + field[1][i].label + field[2][i].label;
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

    private void checkTopLeftDiagonal(Cell[][] field) {
        String topLeftDiagonal = field[0][0].label + field[1][1].label + field[2][2].label;
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

    private void checkTopRightDiagonal(Cell[][] field) {
        String topRightDiagonal = field[0][2].label + field[1][1].label + field[2][0].label;
        checkSequences(topRightDiagonal);
        int emptyCellCoordinate = topRightDiagonal.indexOf(' ');

        if (tableHas2X) {
            sequenceRowX = emptyCellCoordinate;

            sequenceColumnX = switch (emptyCellCoordinate) {
                case 0 -> 2;
                case 1 -> 1;
                case 2 -> 0;
                default -> throw new IllegalStateException("Unexpected value: " + emptyCellCoordinate);
            };
        } else if (tableHas2O) {
            sequenceRowO = emptyCellCoordinate;

            sequenceColumnO = switch (emptyCellCoordinate) {
                case 0 -> 2;
                case 1 -> 1;
                case 2 -> 0;
                default -> throw new IllegalStateException("Unexpected value: " + emptyCellCoordinate);
            };
        }
    }

    private void checkSequences(String sequence) {
        if ("XXX".equals(sequence)) {
            tableHas3X = true;
        } else if ("OOO".equals(sequence)) {
            tableHas3O = true;
        } else if (sequence.matches("XX ")) {
            tableHas2X = true;
        } else if (sequence.matches("OO ")) {
            tableHas2O = true;
        }
    }
}
