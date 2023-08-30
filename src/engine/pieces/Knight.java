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
    public List<Move> calculateMoves(Board board) {
        List<Move> possibleMoves = new ArrayList<>();

        //check if in bounds of board
        // x+-1 and y+-2
        for (int[] offsets : offsets) {
            Position possibleDestination = new Position(this.position.getX() + offsets[0], this.position.getY() + offsets[1]);
            if (Utility.checkIfInBounds(possibleDestination.getX(), possibleDestination.getY())) {
                //get cell at destination
                Cell destinationCell = board.getCellAt(possibleDestination.getX(), possibleDestination.getY());
                if (!destinationCell.isOccupied()) {
                    //new regular move
                    possibleMoves.add(new PassiveMove(board, this, possibleDestination));
                }
                else {
                    //check team
                    if (destinationCell.getPiece().getTeam() != this.getTeam()) {
                        //attack enemy
                        possibleMoves.add(new AttackMove(board, this, possibleDestination, destinationCell.getPiece()));
                    }
                }

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
}
