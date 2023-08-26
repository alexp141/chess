package engine.pieces;

import engine.Team;
import engine.board.Board;
import engine.board.Cell;
import engine.board.Move;
import engine.util.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{

    private int[][] possibleOffsets = {{1,2}, {-1,2}, {1,-2}, {-1,-2}, {2,1}, {2,-1}, {-2,1}, {-2,-1}}; //[x,y] coordinates
    public Knight(Position position, Team team) {
        super(position, team);
    }

    @Override
    public List<Move> calculateMoves(Board board) {
        List<Move> possibleMoves = new ArrayList<>();

        //check if in bounds of board
        // x+-1 and y+-2
        for (int[] offsets : possibleOffsets) {
            if (checkIfInBounds(this.position, offsets[0], offsets[1])) {
                //get cell at destination
                Cell destinationCell = board.getCellAt(this.position.getX() + offsets[0], this.position.getY() + offsets[1]);
                if (!destinationCell.isOccupied()) {
                    //new regular move
                }
                else {
                    //check team
                    if (destinationCell.getPiece().getTeam() != this.getTeam()) {
                        //attack
                    }
                }

            } else {
                continue;
            }
        }

        return possibleMoves;
    }

    private boolean checkIfInBounds(Position curr, int newX, int newY) {
        if(curr.getX() + newX >= 0 && curr.getX() + newX <= 7 &&
            curr.getY() + newY >= 0 && curr.getY() + newY <= 7) {
            return true;
        }
        return false;
    }
}
