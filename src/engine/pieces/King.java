package engine.pieces;

import engine.Team;
import engine.board.*;
import engine.util.Position;
import engine.util.Utility;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    private final int[][] offsets = {{-1,-1}, {0,-1}, {1,-1}, {1,0}, {1,1}, {0,1}, {-1, 1}, {-1, 0}};
    public King(Position position, Team team) {
        super(position, team);
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

            Utility.calculateMoveType(board,this, destination.getX(), destination.getY(), possibleMoves);
        }

        if (this.isFirstMove) {
            //castle move
        }
        return possibleMoves;
    }

}
