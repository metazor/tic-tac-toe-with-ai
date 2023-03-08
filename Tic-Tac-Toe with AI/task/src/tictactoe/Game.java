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
    private boolean currentRoundIsX = true;

    Game(Table table) {
        this.table = table;
        UI.printTable(table.grid);
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
            UI.printTable(table.grid);
            checkTable = new CheckTable(table.grid);
            currentRoundIsX = !currentRoundIsX;
        } while (!(checkTable.tableHas3X || checkTable.tableHas3O || !Table.hasEmptyCells(table.grid)));

        if (checkTable.tableHas3X) {
            UI.printEndMessage("x wins");
        } else if (checkTable.tableHas3O) {
            UI.printEndMessage("o wins");
        } else if (!Table.hasEmptyCells(table.grid)) {
            UI.printEndMessage("draw");
        }
    }

    private void makeMove(String playerType) {
        TargetCoordinates targetCoordinates = switch (playerType) {
            case "user" -> new User(table.grid).makeCoordinates();
            case "easy" -> new EasyAI(table.grid).makeCoordinates();
            case "medium" -> new MediumAI(table.grid, currentRoundIsX).makeCoordinates();
            case "hard" -> new HardAI(table.grid, currentRoundIsX).makeCoordinates();
            default -> throw new IllegalStateException(UNEXPECTED_VALUE + playerType);
        };

        if (!"user".equals(playerType)) {
            UI.printMakingMoveMessage(playerType);
        }

        table.addMarkToTargetCell(targetCoordinates, currentRoundIsX);
    }
}
