package tictactoe.players.minimax;

import tictactoe.Cell;
import tictactoe.CheckTable;
import tictactoe.Game;
import tictactoe.TargetCoordinates;
import tictactoe.players.Player;

import java.util.Arrays;

public class HardAI implements Player {

    private final boolean currentRoundIsX;
    private Cell[][] grid = new Cell[Game.GRID_SIZE][Game.GRID_SIZE];
    private int bestRow;
    private int bestColumn;
    private Cell maxPlayer;
    private Cell minPlayer;

    public HardAI(boolean currentRoundIsX) {
        this.currentRoundIsX = currentRoundIsX;
    }

    @Override
    public TargetCoordinates makeCoordinates(Cell[][] grid) {
        for (int i = 0; i < Game.GRID_SIZE; i++) {
            this.grid[i] = Arrays.copyOf(grid[i], Game.GRID_SIZE);
        }

        if (currentRoundIsX) {
            findBestMove(Cell.CROSS);
        } else {
            findBestMove(Cell.ZERO);
        }

        return new TargetCoordinates(bestRow, bestColumn);
    }

    private void findBestMove(Cell player) {
        maxPlayer = player;

        if (maxPlayer == Cell.CROSS) {
            minPlayer = Cell.ZERO;
        } else {
            minPlayer = Cell.CROSS;
        }

        Move bestMove = maximize(player, true);
        bestRow = bestMove.getRow();
        bestColumn = bestMove.getColumn();
    }

    private Move maximize(Cell player, boolean maximizingPlayer) {
        Move move = maximizingPlayer ? new Move(evaluateTable(player)) : new Move(-evaluateTable(player));

        if (move.getScore() != 0) {
            return move;
        }

        if (!CheckTable.hasEmptyCells(grid)) {
            return move;
        }

        int bestScore = maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < Game.GRID_SIZE; i++) {
            for (int j = 0; j < Game.GRID_SIZE; j++) {
                if (grid[i][j] == Cell.EMPTY) {
                    grid[i][j] = maximizingPlayer ? maxPlayer : minPlayer;
                    int score = maximizingPlayer ? maximize(minPlayer, false).getScore()
                            : maximize(maxPlayer, true).getScore();
                    grid[i][j] = Cell.EMPTY;

                    if (maximizingPlayer ? (score > bestScore) : (score < bestScore)) {
                        bestScore = score;
                        move.setScore(score);
                        move.setRow(i);
                        move.setColumn(j);
                    }
                }
            }
        }

        return move;
    }

    private int evaluateTable(Cell player) {
        CheckTable checkTable = new CheckTable(grid);

        if (player == Cell.CROSS) {
            if (checkTable.has3X()) {
                return 1;
            } else if (checkTable.has3O()) {
                return -1;
            }
        } else {
            if (checkTable.has3O()) {
                return 1;
            } else if (checkTable.has3X()) {
                return -1;
            }
        }

        return 0;
    }
}
