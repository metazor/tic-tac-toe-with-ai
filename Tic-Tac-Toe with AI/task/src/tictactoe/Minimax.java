package tictactoe;

public class Minimax {

    private final Cell[][] grid;
    private int bestRow;
    private int bestColumn;
    private Cell maxPlayer;
    private Cell minPlayer;
    public Minimax(Cell[][] grid) {
        this.grid = grid.clone();
    }

    public int getBestRow() {
        return bestRow;
    }

    public int getBestColumn() {
        return bestColumn;
    }

    public void findBestMove(Cell player) {
        maxPlayer = player;

        if (maxPlayer == Cell.CROSS) {
            minPlayer = Cell.ZERO;
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

        if (!Table.hasEmptyCells(grid)) {
            return move;
        }

        int bestScore = maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < Table.GRID_SIZE; i++) {
            for (int j = 0; j < Table.GRID_SIZE; j++) {
                if (grid[i][j] == Cell.EMPTY) {
                    grid[i][j] = maximizingPlayer ? maxPlayer : minPlayer;
                    int score = maximizingPlayer ? maximize(minPlayer, false).score : maximize(maxPlayer, true).score;
                    grid[i][j] = Cell.EMPTY;

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
        CheckTable checkTable = new CheckTable(grid);

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
