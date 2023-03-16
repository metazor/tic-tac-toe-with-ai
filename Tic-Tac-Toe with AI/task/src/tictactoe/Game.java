package tictactoe;

import tictactoe.players.EasyAI;
import tictactoe.players.HardAI;
import tictactoe.players.MediumAI;
import tictactoe.players.User;

class Game {

    static final String UNEXPECTED_VALUE = "Unexpected value: ";
    private static final int FIRST_PLAYER = 1;
    private static final int SECOND_PLAYER = 2;
    private final Table table;
    private final UI ui;
    private boolean currentRoundIsX = true;

    Game(Table table, UI ui) {
        this.table = table;
        this.ui = ui;
        ui.printTable(table.getGrid());
    }

    void runGame(String[] command) {
        CheckTable checkTable;

        do {
            int currentPlayer;

            if (currentRoundIsX) {
                currentPlayer = FIRST_PLAYER;
            } else {
                currentPlayer = SECOND_PLAYER;
            }

            makeMove(command[currentPlayer]);
            ui.printTable(table.getGrid());
            checkTable = new CheckTable(table.getGrid());
            currentRoundIsX = !currentRoundIsX;
        } while (!(checkTable.has3X() || checkTable.has3O() || !Table.hasEmptyCells(table.getGrid())));

        printEndMessage(checkTable);
    }

    private void makeMove(String playerType) {
        TargetCoordinates targetCoordinates = switch (playerType) {
            case "user" -> new User(table.getGrid(), ui).makeCoordinates();
            case "easy" -> new EasyAI(table.getGrid()).makeCoordinates();
            case "medium" -> new MediumAI(table.getGrid(), currentRoundIsX).makeCoordinates();
            case "hard" -> new HardAI(table.getGrid(), currentRoundIsX).makeCoordinates();
            default -> throw new IllegalStateException(UNEXPECTED_VALUE + playerType);
        };

        if (!"user".equals(playerType)) {
            ui.printMakingMoveMessage(playerType);
        }

        table.addMarkToTargetCell(targetCoordinates, currentRoundIsX);
    }

    private void printEndMessage(CheckTable checkTable) {
        if (checkTable.has3X()) {
            ui.printMessage("x wins");
        } else if (checkTable.has3O()) {
            ui.printMessage("o wins");
        } else if (!Table.hasEmptyCells(table.getGrid())) {
            ui.printMessage("draw");
        }
    }
}
