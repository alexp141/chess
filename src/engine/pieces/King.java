package engine.pieces;

import engine.Team;
import engine.board.*;
import engine.util.Position;
import engine.util.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class King extends Piece{
    private final int[][] offsets = {{-1,-1}, {0,-1}, {1,-1}, {1,0}, {1,1}, {0,1}, {-1, 1}, {-1, 0}};
    public King(Position position, Team team) {
        super(position, team);
    }

    @Override
    public String getPieceFilename() {
        return getTeam() == Team.WHITE ? "wk.png" : "bk.png";
    }

    @Override
    public Piece copy() {
        return new King(new Position(this.position), team);
    }

    @Override
    public List<Move> calculateMoves(Board board) {
        List<Move> possibleMoves = new ArrayList<>();

        for (int[] offset : offsets) {
            int dx = offset[0];
            int dy = offset[1];
            Position destination = new Position(this.position.getX() + dx, this.position.getY() + dy);
            //check bounds
            if (!Utility.checkIfInBounds(destination.getX(), destination.getY())) {
                continue;
            }

            Cell destinationCell = board.getCellAt(destination.getX(), destination.getY());
            if (this.team == Team.WHITE) {
                if (destinationCell.isAttackedByBlack())
                    continue;
            }
            else {
                if (destinationCell.isAttackedByWhite())
                    continue;
            }

            if (destinationCell.isOccupied()) {
                Piece pieceAtDestination = destinationCell.getPiece();
                if (pieceAtDestination.getTeam() != this.getTeam()) {
                    possibleMoves.add(new AttackMove(board, this, new Position(this.position), new Position(destination.getX(), destination.getY()), pieceAtDestination));
                }

            }
            else {
                possibleMoves.add(new PassiveMove(board, this, new Position(this.position), new Position(destination.getX(), destination.getY())));
            }
        }
        //TODO CASTLING MOVES
        if (this.isFirstMove) {
            if (this.team == Team.WHITE) {
                Cell r1 = board.getCellAt(5,7);
                Cell r2 = board.getCellAt(6,7);
                Cell r3 = board.getCellAt(7,7);
                if (!r1.isOccupied() && !r1.isAttackedByBlack() && !r2.isOccupied() && !r2.isAttackedByBlack()) {
                    Piece rook = r3.getPiece();
                    if (rook instanceof Rook && rook.getTeam() == Team.WHITE && rook.isFirstMove) {
                        possibleMoves.add(new KingsideCastle(board, this, new Position(this.position), new Position(6,7), rook));
                    }
                }
                r1 = r2 = r3 = null;
                Cell l1 = board.getCellAt(3, 7);
                Cell l2 = board.getCellAt(2, 7);
                Cell l3 = board.getCellAt(1, 7);
                Cell l4 = board.getCellAt(0, 7);
                if (!l1.isOccupied() && !l1.isAttackedByBlack() && !l2.isOccupied() && !l2.isAttackedByBlack() && !l3.isOccupied() && !l3.isAttackedByBlack()) {
                    Piece rook = l4.getPiece();
                    if (rook instanceof Rook && rook.getTeam() == Team.WHITE && rook.isFirstMove) {
                        possibleMoves.add(new QueensideCastle(board, this, new Position(this.position), new Position(2,7), rook));
                    }
                }
            }
            else {
                Cell r1 = board.getCellAt(5,0);
                Cell r2 = board.getCellAt(6,0);
                Cell r3 = board.getCellAt(7,0);
                if (!r1.isOccupied() && !r1.isAttackedByWhite() && !r2.isOccupied() && !r2.isAttackedByWhite()) {
                    Piece rPiece = r3.getPiece();
                    if (rPiece instanceof Rook && rPiece.getTeam() == Team.BLACK && rPiece.isFirstMove) {
                        possibleMoves.add(new KingsideCastle(board, this, new Position(this.position), new Position(6,0), rPiece));
                    }
                }
            }
        }
        return possibleMoves;
    }

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof King)) {
            return false;
        }
        King king = (King) o;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(offsets);
        return result;
    }

    @Override
    public String toString() {
        return "K";
    }

}
