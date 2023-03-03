package tictactoe;

import java.util.Scanner;

class Main {

    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String command;

        do {
            System.out.print("Input command: ");
            command = scanner.nextLine();

            if ("exit".equals(command)) {
                return;
            }
        } while (!isValidCommand(command));

        Table table = new Table();
        table.printTable();
        Game game = new Game();
        game.runGame(command.split(" "), table);
    }

    private static boolean isValidCommand(String command) {
        if (!command.matches("^start (user|easy|medium|hard) (user|easy|medium|hard)")) {
            System.out.println("Bad parameters!");
            return false;
        }

        return true;
    }
}
