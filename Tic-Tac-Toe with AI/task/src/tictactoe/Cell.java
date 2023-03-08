package tictactoe;

public enum Cell {
    CROSS("X"),
    ZERO("O"),
    EMPTY(" ");

    final String label;

    Cell(String label) {
        this.label = label;
    }
}
