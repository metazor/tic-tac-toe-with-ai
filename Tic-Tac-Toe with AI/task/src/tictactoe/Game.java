package tictactoe;

import tictactoe.players.EasyAI;
import tictactoe.players.MediumAI;
import tictactoe.players.minimax.HardAI;
import tictactoe.players.User;

import java.util.Arrays;
import java.util.Random;
import java.util.random.RandomGenerator;

public class Game {

    public static final int GRID_SIZE = 3;
    public static final RandomGenerator random = new Random();
    static final String UNEXPECTED_VALUE_MESSAGE = "Unexpected value: ";
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
        } while (!(checkTable.has3X() || checkTable.has3O() || !CheckTable.hasEmptyCells(grid)));

        printEndMessage(checkTable);
    }

    private void makeMove(String playerType) {
        TargetCoordinates targetCoordinates = switch (playerType) {
            case "user" -> new User(ui).makeCoordinates(grid);
            case "easy" -> new EasyAI().makeCoordinates(grid);
            case "medium" -> new MediumAI(currentRoundIsX).makeCoordinates(grid);
            case "hard" -> new HardAI(currentRoundIsX).makeCoordinates(grid);
            default -> throw new IllegalStateException(UNEXPECTED_VALUE_MESSAGE + playerType);
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
        } else if (!CheckTable.hasEmptyCells(grid)) {
            ui.printMessage("draw");
        }
    }

    private enum PlayerOrder {
        FIRST_PLAYER, SECOND_PLAYER
    }
}
