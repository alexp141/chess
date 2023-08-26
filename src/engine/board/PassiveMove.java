package engine.board;

import engine.pieces.Piece;
import engine.util.Position;

public class PassiveMove extends Move{

    public PassiveMove(Board board, Piece movingPiece, Position destination) {
        super(board, movingPiece, destination);
    }


}
