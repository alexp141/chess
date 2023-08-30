package engine.util;

import engine.pieces.Piece;

import java.util.Objects;

public class Position {
    private int x; //x coordinate
    private int y; //y coordinate

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Position)) {
            return false;
        }

        Position p = (Position) o;
        return p.getX() == this.x && p.getY() == this.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
