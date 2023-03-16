package tictactoe;

public enum Cell {
    CROSS("X"),
    ZERO("O"),
    EMPTY(" ");

    private final String label;

    Cell(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
