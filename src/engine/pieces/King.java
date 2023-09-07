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
            if (this.getTeam() == Team.WHITE) {
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
            //castle move
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

}
