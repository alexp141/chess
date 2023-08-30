package engine.board;

import engine.pieces.Piece;
import engine.util.Position;

public abstract class Move {
    protected Board board;
    protected Piece movingPiece;
    protected Position start;
    protected Position destination;

    public Move(Board board, Piece movingPiece, Position start, Position destination) {
        this.board = board;
        this.movingPiece = movingPiece;
        this.start = start;
        this.destination = destination;
    }

    public Position getDestination() {
        return this.destination;
    }
    //TODO add equals and hashcode methods
    public abstract Board executeMove();

    public abstract Board undoMove();
}
