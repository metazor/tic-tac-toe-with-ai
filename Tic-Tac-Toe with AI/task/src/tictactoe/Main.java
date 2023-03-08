package tictactoe;

class Main {

    public static void main(String[] args) {
        String command = UI.readCommand();

        if ("exit".equals(command)) {
            return;
        }

        Table table = new Table();
        Game game = new Game(table);
        game.runGame(command.split(" "));
    }
}
