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

    public Position getDestination() {
        return this.destination;
    }

    public abstract Board executeMove(Board board); //decide between current board and copy of board
}
