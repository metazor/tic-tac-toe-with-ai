package tictactoe;

import java.util.Scanner;

public final class UI {

    private static final int LINE_LENGTH = 9;
    private static final int REQUIRED_COORDINATES = 2;
    private static final Scanner scanner = new Scanner(System.in);
    private static final String VALID_COMMANDS = "^start (user|easy|medium|hard) (user|easy|medium|hard)";

    private UI() {
    }

    static void printTable(Cell[][] grid) {
        System.out.println("-".repeat(LINE_LENGTH));

        for (int i = 0; i < Table.GRID_SIZE; i++) {
            System.out.printf("| %s %s %s |%n", grid[i][0].label, grid[i][1].label, grid[i][2].label);
        }

        System.out.println("-".repeat(LINE_LENGTH));
    }

    static String readCommand() {
        String command;

        do {
            System.out.print("Input command: ");
            command = scanner.nextLine();

            if ("exit".equals(command)) {
                return command;
            }

            if (!command.matches(VALID_COMMANDS)) {
                System.out.println("Bad parameters!");
            }
        } while (!command.matches(VALID_COMMANDS));

        return command;
    }

    static void printEndMessage(String message) {
        switch (message) {
            case "x wins" -> System.out.println("X wins");
            case "o wins" -> System.out.println("O wins");
            case "draw" -> System.out.println("Draw");
        }
    }

    static void printMakingMoveMessage(String playerType) {
        System.out.printf("Making move level \"%s\"%n", playerType);
    }

    public static TargetCoordinates readCoordinates(Cell[][] grid) {
        String[] input;

        do {
            do {
                input = checkCoordinates();
            } while (input.length != REQUIRED_COORDINATES || !isNumber(input));

            int row = Integer.parseInt(input[0]);
            int column = Integer.parseInt(input[1]);

            if (row < 1 || row > Table.GRID_SIZE || column < 1 || column > Table.GRID_SIZE) {
                System.out.printf("Coordinates should be from 1 to %d!%n", Table.GRID_SIZE);
            } else if (grid[row - 1][column - 1] != Cell.EMPTY) {
                System.out.println("This cell is occupied! Choose another one!");
            }
        } while (!isValidInput(grid, input));

        TargetCoordinates targetCoordinates = new TargetCoordinates();
        targetCoordinates.setRow(Integer.parseInt(input[0]) - 1);
        targetCoordinates.setColumn(Integer.parseInt(input[1]) - 1);
        return targetCoordinates;
    }

    private static String[] checkCoordinates() {
        System.out.print("Enter the coordinates: ");
        String[] input = scanner.nextLine().split(" ");

        if (!isNumber(input)) {
            System.out.println("You should enter numbers!");
        }

        return input;
    }

    private static boolean isNumber(String[] coordinates) {
        for (String coordinate : coordinates) {
            try {
                Integer.parseInt(coordinate);
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }

    private static boolean isValidInput(Cell[][] grid, String[] input) {
        int row = Integer.parseInt(input[0]);
        int column = Integer.parseInt(input[1]);
        return row >= 1 && row <= Table.GRID_SIZE && column >= 1 && column <= Table.GRID_SIZE
               && grid[row - 1][column - 1] == Cell.EMPTY;
    }
}
