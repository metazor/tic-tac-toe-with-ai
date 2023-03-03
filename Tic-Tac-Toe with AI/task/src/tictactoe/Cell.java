package tictactoe;

enum Cell {
    CROSS("X"),
    NOUGHT("O"),
    EMPTY(" ");

    final String label;

    Cell(String label) {
        this.label = label;
    }
}
