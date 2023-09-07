package engine.pieces;

import engine.Team;
import engine.board.*;
import engine.util.Position;
import engine.util.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knight extends Piece{
    private int[][] offsets = {{1,2}, {-1,2}, {1,-2}, {-1,-2}, {2,1}, {2,-1}, {-2,1}, {-2,-1}}; //[x,y] coordinates
    public Knight(Position position, Team team) {
        super(position, team);
    }

    @Override
    public String getPieceFilename() {
        return getTeam() == Team.WHITE ? "wkn.png" : "bkn.png";
    }

    @Override
    public Piece copy() {
        return new Knight(new Position(this.position), team);
    }
    @Override
    public List<Move> calculateMoves(Board board) {
        List<Move> possibleMoves = new ArrayList<>();

        //check if in bounds of board
        // x+-1 and y+-2
        for (int[] offsets : offsets) {
            int dx = offsets[0];
            int dy = offsets[1];
            Position destination = new Position(this.position.getX() + dx, this.position.getY() + dy);
            if (Utility.checkIfInBounds(destination.getX(), destination.getY())) {
                Board.calculateMoveType(board,this, destination.getX(), destination.getY(), possibleMoves);
            } else {
                continue;
            }
        }

        return possibleMoves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Knight)) return false;
        Knight knight = (Knight) o;
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
        return "N";
    }
}
