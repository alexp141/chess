package engine.board;

import engine.Team;
import engine.pieces.Piece;
import engine.util.Position;

public class QueensideCastle extends Move {
    private Team team;
    private Piece rook;

    public QueensideCastle(Board board, Piece movingPiece, Position start, Position destination, Piece rook) {
        super(board, movingPiece, start, destination);
        this.team = movingPiece.getTeam();
        this.rook = rook;
    }

    @Override
    public void executeMove() {
        this.board.placePiece(movingPiece, destination.getX(), destination.getY());
        this.board.placePiece(rook, start.getX() - 1, start.getY());
        movingPiece.getPosition().updatePosition(destination.getX(),destination.getY());
        rook.getPosition().updatePosition(start.getX() - 1,start.getY());
        this.board.removePiece(start.getX(), start.getY());
        this.board.removePiece(destination.getX() - 2, destination.getY());
        rook.setPieceMoved();
        this.board.updatePossibleMovesWhite();
        this.board.updatePossibleMovesBlack();
        this.board.updateCellData();
    }

    @Override
    public void undoMove() {
        this.board.placePiece(movingPiece, start.getX(), start.getY());
        this.board.placePiece(rook, destination.getX() - 2, destination.getY());
        movingPiece.getPosition().updatePosition(start.getX(),start.getY());
        rook.getPosition().updatePosition(destination.getX() - 2,destination.getY());
        this.board.removePiece(destination.getX(), destination.getY());
        this.board.removePiece(start.getX() - 1, start.getY());
        rook.unsetPieceMoved();
        this.board.updatePossibleMovesWhite();
        this.board.updatePossibleMovesBlack();
        this.board.updateCellData();
    }
}
