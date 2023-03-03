package tictactoe;

class Game {

    private enum Player {
        FIRST(1),
        SECOND(2);

        final int order;

        Player(int order) {
            this.order = order;
        }
    }

    void runGame(String[] command, Table table) {
        do {
            Player currentPlayer;

            if (table.currentRoundIsX) {
                currentPlayer = Player.FIRST;
            } else {
                currentPlayer = Player.SECOND;
            }

            table.makeCoordinates(command[currentPlayer.order]);
            table.currentRoundIsX = !table.currentRoundIsX;
        } while (!table.isFinished());
    }
}
