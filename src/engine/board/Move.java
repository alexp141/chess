package engine.board;

import engine.pieces.Piece;
import engine.util.Position;

public abstract class Move {
    protected Board board;
    protected Piece movingPiece;
    protected Position destination;

    public Move(Board board, Piece movingPiece, Position destination) {
        this.board = board;
        this.movingPiece = movingPiece;
        this.destination = destination;
    }

}
