package engine.board;

import engine.Team;
import engine.pieces.Piece;
import engine.util.Position;

public class KingsideCastle extends Move {
    private Team team;
    private Piece rook;

    public KingsideCastle(Board board, Piece movingPiece, Position start, Position destination, Piece rook) {
        super(board, movingPiece, start, destination);
        this.team = movingPiece.getTeam();
        this.rook = rook;
    }

    @Override
    public void executeMove() {
        this.board.placePiece(movingPiece, destination.getX(), destination.getY());
        System.out.println(this.board.toString());
        this.board.placePiece(rook, start.getX() + 1, start.getY());
        System.out.println(this.board.toString());
        movingPiece.getPosition().updatePosition(destination.getX(),destination.getY());
        rook.getPosition().updatePosition(start.getX() + 1,start.getY());
        this.board.removePiece(start.getX(), start.getY());
        this.board.removePiece(destination.getX() + 1, destination.getY());
        this.board.updatePossibleMovesWhite();
        this.board.updatePossibleMovesBlack();
        this.board.updateCellData();
    }

    @Override
    public void undoMove() {
        this.board.placePiece(movingPiece, start.getX(), start.getY());
        System.out.println(this.board.toString());
        this.board.placePiece(rook, destination.getX() + 1, destination.getY());
        System.out.println(this.board.toString());
        movingPiece.getPosition().updatePosition(start.getX(),start.getY());
        rook.getPosition().updatePosition(destination.getX() + 1,destination.getY());
        this.board.updatePossibleMovesWhite();
        this.board.updatePossibleMovesBlack();
        this.board.updateCellData();
    }
}
