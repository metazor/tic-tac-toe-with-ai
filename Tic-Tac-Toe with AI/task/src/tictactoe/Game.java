package tictactoe;

import tictactoe.players.EasyAI;
import tictactoe.players.MediumAI;
import tictactoe.players.minimax.HardAI;
import tictactoe.players.User;

import java.util.Arrays;

public class Game {

    public static final int GRID_SIZE = 3;
    static final String UNEXPECTED_VALUE = "Unexpected value: ";
    private final UI ui;
    private final Cell[][] grid = new Cell[GRID_SIZE][GRID_SIZE];
    private boolean currentRoundIsX = true;

    Game(UI ui) {
        this.ui = ui;

        for (Cell[] cells : grid) {
            Arrays.fill(cells, Cell.EMPTY);
        }

        ui.printTable(grid);
    }

    void runGame(String[] command) {
        CheckTable checkTable;

        do {
            int currentPlayer = currentRoundIsX ? (PlayerOrder.FIRST_PLAYER.ordinal() + 1)
                    : (PlayerOrder.SECOND_PLAYER.ordinal() + 1);
            makeMove(command[currentPlayer]);
            ui.printTable(grid);
            checkTable = new CheckTable(grid);
            currentRoundIsX = !currentRoundIsX;
        } while (!(checkTable.has3X() || checkTable.has3O() || !hasEmptyCells(grid)));

        printEndMessage(checkTable);
    }

    public static boolean hasEmptyCells(Cell[][] grid) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j] == Cell.EMPTY) {
                    return true;
                }
            }
        }

        return false;
    }

    private void makeMove(String playerType) {
        TargetCoordinates targetCoordinates = switch (playerType) {
            case "user" -> new User(grid, ui).makeCoordinates();
            case "easy" -> new EasyAI(grid).makeCoordinates();
            case "medium" -> new MediumAI(grid, currentRoundIsX).makeCoordinates();
            case "hard" -> new HardAI(grid, currentRoundIsX).makeCoordinates();
            default -> throw new IllegalStateException(UNEXPECTED_VALUE + playerType);
        };

        if (!"user".equals(playerType)) {
            ui.printMakingMoveMessage(playerType);
        }

        addMarkToTargetCell(targetCoordinates, currentRoundIsX);
    }

    private void addMarkToTargetCell(TargetCoordinates targetCoordinates, boolean currentRoundIsX) {
        if (currentRoundIsX) {
            grid[targetCoordinates.getRow()][targetCoordinates.getColumn()] = Cell.CROSS;
        } else {
            grid[targetCoordinates.getRow()][targetCoordinates.getColumn()] = Cell.ZERO;
        }
    }

    private void printEndMessage(CheckTable checkTable) {
        if (checkTable.has3X()) {
            ui.printMessage("x wins");
        } else if (checkTable.has3O()) {
            ui.printMessage("o wins");
        } else if (!hasEmptyCells(grid)) {
            ui.printMessage("draw");
        }
    }

    private enum PlayerOrder {
        FIRST_PLAYER, SECOND_PLAYER
    }
}
