package engine.board;

import engine.pieces.Piece;
import engine.util.Position;

public abstract class Cell {
    protected Position position;

    public Cell(Position position) {
        this.position = position;
    }
    public abstract boolean isOccupied();
    public abstract Piece getPiece();

}
