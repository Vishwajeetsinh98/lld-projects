package board;

import pieces.Piece;

import java.util.Objects;

public class Square {
    private final String id;
    private final int x;
    private final int y;
    private Piece piece;

    public Square(String id, int x, int y, Piece piece) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public String getId() {return id;}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public char getFile() {
        return id.split("_")[0].charAt(0);
    }

    public int getRank() {
        return id.split("_")[1].charAt(0) - '0';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Square other)) return false;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return id + ": (" + x + ", " + y  + ")";
    }
}
