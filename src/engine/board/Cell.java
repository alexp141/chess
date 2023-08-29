package engine.board;

import engine.pieces.Piece;
import engine.util.Position;

public abstract class Cell {

    public abstract boolean isOccupied();
    public abstract Piece getPiece();
}
