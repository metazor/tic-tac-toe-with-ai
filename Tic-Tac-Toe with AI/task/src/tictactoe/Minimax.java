package tictactoe;

class Minimax {

    int bestRow;
    int bestColumn;
    private Cell maxPlayer;
    private Cell minPlayer;
    private final Cell[][] field;

    Minimax(Cell[][] field) {
        this.field = field.clone();
    }

    void findBestMove(Cell player) {
        maxPlayer = player;

        if (maxPlayer == Cell.CROSS) {
            minPlayer = Cell.NOUGHT;
        } else {
            minPlayer = Cell.CROSS;
        }

        Move bestMove = maximize(player, true);
        bestRow = bestMove.row;
        bestColumn = bestMove.column;
    }

    private Move maximize(Cell player, boolean maximizingPlayer) {
        Move move = maximizingPlayer ? new Move(evaluateTable(player)) : new Move(-evaluateTable(player));

        if (move.score != 0) {
            return move;
        }

        if (!Table.hasEmptyCells(field)) {
            return move;
        }

        int bestScore = maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < Table.FIELD_SIZE; i++) {
            for (int j = 0; j < Table.FIELD_SIZE; j++) {
                if (field[i][j] == Cell.EMPTY) {
                    field[i][j] = maximizingPlayer ? maxPlayer : minPlayer;
                    int score = maximizingPlayer ? maximize(minPlayer, false).score : maximize(maxPlayer, true).score;
                    field[i][j] = Cell.EMPTY;

                    if (maximizingPlayer ? (score > bestScore) : (score < bestScore)) {
                        bestScore = score;
                        move.score = score;
                        move.row = i;
                        move.column = j;
                    }
                }
            }
        }

        return move;
    }

    private int evaluateTable(Cell player) {
        CheckTable checkTable = new CheckTable();
        checkTable.checkLines(field);

        if (player == Cell.CROSS) {
            if (checkTable.tableHas3X) {
                return 1;
            } else if (checkTable.tableHas3O) {
                return -1;
            }
        } else {
            if (checkTable.tableHas3O) {
                return 1;
            } else if (checkTable.tableHas3X) {
                return -1;
            }
        }

        return 0;
    }
}
