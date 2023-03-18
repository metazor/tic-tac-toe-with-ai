package tictactoe;

class Main {

    public static void main(String[] args) {
        UI ui = new UI();
        String command = ui.readCommand();

        if ("exit".equals(command)) {
            return;
        }

        Game game = new Game(ui);
        game.runGame(command.split(" "));
    }
}
