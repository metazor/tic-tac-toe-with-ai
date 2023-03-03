package tictactoe;

import java.util.Arrays;
import java.util.Random;
import java.util.random.RandomGenerator;

class Table {

    public static final int FIELD_SIZE = 3;
    public static final int LINE_LENGTH = 9;
    public static final int REQUIRED_COORDINATES = 2;
    boolean currentRoundIsX = true;
    private final Cell[][] field = new Cell[FIELD_SIZE][FIELD_SIZE];
    private final CheckTable checkTable = new CheckTable();
    private int targetRow;
    private int targetColumn;

    Table() {
        for (Cell[] cells : field) {
            Arrays.fill(cells, Cell.EMPTY);
        }
    }

    void printTable() {
        System.out.println("-".repeat(LINE_LENGTH));

        for (int i = 0; i < FIELD_SIZE; i++) {
            System.out.printf("| %s %s %s |%n", field[i][0].label, field[i][1].label, field[i][2].label);
        }

        System.out.println("-".repeat(LINE_LENGTH));
    }

    void makeCoordinates(String playerType) {
        switch (playerType) {
            case "user" -> readCoordinates();
            case "easy" -> makeEasyCoordinates();
            case "medium" -> makeMediumCoordinates();
            case "hard" -> makeHardCoordinates();
        }

        if (!"user".equals(playerType)) {
            System.out.printf("Making move level \"%s\"%n", playerType);
        }

        addMarkToTargetCell();
        printTable();
        checkTable.checkLines(field);
    }

    private void readCoordinates() {
        String[] input;

        do {
            do {
                System.out.print("Enter the coordinates: ");
                input = Main.scanner.nextLine().split(" ");
            } while (input.length != REQUIRED_COORDINATES || !isNumber(input));
        } while (!isValidInput(input));

        targetRow = Integer.parseInt(input[0]) - 1;
        targetColumn = Integer.parseInt(input[1]) - 1;
    }

    private static boolean isNumber(String[] coordinates) {
        for (String coordinate : coordinates) {
            try {
                Integer.parseInt(coordinate);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                return false;
            }
        }

        return true;
    }

    private boolean isValidInput(String[] input) {
        int parsedRow = Integer.parseInt(input[0]) - 1;
        int parsedColumn = Integer.parseInt(input[1]) - 1;

        if (parsedRow + 1 < 1 || parsedRow + 1 > FIELD_SIZE || parsedColumn + 1 < 1 || parsedColumn + 1 > FIELD_SIZE) {
            System.out.printf("Coordinates should be from 1 to %d!%n", FIELD_SIZE);
            return false;
        } else if (field[parsedRow][parsedColumn] != Cell.EMPTY) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        return true;
    }

    private void makeEasyCoordinates() {
        RandomGenerator random = new Random();
        int randomRow = random.nextInt(FIELD_SIZE);
        int randomColumn = random.nextInt(FIELD_SIZE);

        while (field[randomRow][randomColumn] != Cell.EMPTY) {
            randomRow = random.nextInt(FIELD_SIZE);
            randomColumn = random.nextInt(FIELD_SIZE);
        }

        targetRow = randomRow;
        targetColumn = randomColumn;
    }

    private void makeMediumCoordinates() {
        checkTable.checkLines(field);

        if (currentRoundIsX) {
            if (checkTable.tableHas2X) {
                setTargetCoordinates(Cell.CROSS);
            } else if (checkTable.tableHas2O) {
                setTargetCoordinates(Cell.NOUGHT);
            } else {
                makeEasyCoordinates();
            }
        } else {
            if (checkTable.tableHas2O) {
                setTargetCoordinates(Cell.NOUGHT);
            } else if (checkTable.tableHas2X) {
                setTargetCoordinates(Cell.CROSS);
            } else {
                makeEasyCoordinates();
            }
        }
    }

    private void setTargetCoordinates(Cell labelToSet) {
        if (labelToSet == Cell.CROSS) {
            targetRow = checkTable.sequenceRowX;
            targetColumn = checkTable.sequenceColumnX;
        } else {
            targetRow = checkTable.sequenceRowO;
            targetColumn = checkTable.sequenceColumnO;
        }
    }

    private void makeHardCoordinates() {
        Minimax minimax = new Minimax(field);

        if (currentRoundIsX) {
            minimax.findBestMove(Cell.CROSS);
        } else {
            minimax.findBestMove(Cell.NOUGHT);
        }

        targetRow = minimax.bestRow;
        targetColumn = minimax.bestColumn;
    }

    private void addMarkToTargetCell() {
        if (currentRoundIsX) {
            field[targetRow][targetColumn] = Cell.CROSS;
        } else {
            field[targetRow][targetColumn] = Cell.NOUGHT;
        }
    }

    boolean isFinished() {
        if (checkTable.tableHas3X) {
            System.out.println("X wins");
        } else if (checkTable.tableHas3O) {
            System.out.println("O wins");
        } else if (!hasEmptyCells(field)) {
            System.out.println("Draw");
        } else {
            return false;
        }

        return true;
    }

    static boolean hasEmptyCells(Cell[][] field) {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (field[i][j] == Cell.EMPTY) {
                    return true;
                }
            }
        }

        return false;
    }
}
