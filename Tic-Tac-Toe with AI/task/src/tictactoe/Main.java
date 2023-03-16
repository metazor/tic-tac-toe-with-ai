package tictactoe;

class Main {

    public static void main(String[] args) {
        UI ui = new UI();
        String command = ui.readCommand();

        if ("exit".equals(command)) {
            return;
        }

        Table table = new Table();
        Game game = new Game(table, ui);
        game.runGame(command.split(" "));
    }
}
