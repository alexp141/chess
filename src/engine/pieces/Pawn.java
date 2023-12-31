package engine.pieces;

import engine.Team;
import engine.board.*;
import engine.util.Position;
import engine.util.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {

    private final int[][] offsets = {{-1,-1}, {1,-1}, {0,-1}, {0,-2}};
    public Pawn(Position position, Team team) {
        super(position, team);
    }

    @Override
    public String getPieceFilename() {
        return getTeam() == Team.WHITE ? "wp.png" : "bp.png";
    }

    @Override
    public Piece copy() {
        return new Pawn(new Position(this.position), team);
    }
    @Override
    public List<Move> calculateMoves(Board board) {
        List<Move> possibleMoves = new ArrayList<>();

        for (int[] offset : offsets) {
            int x = offset[0];
            int y = offset[1];
            //get new direction based on whether the team is black or white
            int dx = x * (this.team.getDirection() * -1); //x direction that the piece will move in
            int dy = y * (this.team.getDirection() * -1); //y direction that the piece will move in
            Position destination = new Position(this.position.getX() + dx, this.position.getY() + dy);
            //check bounds
            if(!Utility.checkIfInBounds(destination.getX(),destination.getY())) {
                continue;
            }
            //frontal single move
            if(x == 0 && y == -1) {
                Cell destinationCell = board.getCellAt(destination.getX(), destination.getY());
                if (!destinationCell.isOccupied()) {
                    possibleMoves.add(new PassiveMove(board, this, new Position(this.getPosition()), destination));
                }
            }
            //diagonal attacks
            else if (x == -1 && y == -1 || x == 1 && y == -1) {
                //attack only if enemy piece is there
                Cell destinationCell = board.getCellAt(destination.getX(), destination.getY());
                if (destinationCell.isOccupied()) {
                    Piece pieceAtDestination = destinationCell.getPiece();
                    if (pieceAtDestination.getTeam() != this.getTeam()) {
                        possibleMoves.add(new AttackMove(board, this,
                                new Position(this.getPosition()),
                                new Position(pieceAtDestination.position.getX(), pieceAtDestination.position.getY()),
                                pieceAtDestination));
                    }
                }
            }
            //frontal double move
            else if ((x == 0 && y == -2) && this.isFirstMove) {
                Cell destinationCell = board.getCellAt(destination.getX(), destination.getY());
                if (!destinationCell.isOccupied()) {
                    //check if cell behind destination is occupied
                    Cell cellBehindDestination = board.getCellAt(destination.getX() - dx, destination.getY());
                    if (!cellBehindDestination.isOccupied()) {
                        possibleMoves.add(new PassiveMove(board, this, new Position(this.getPosition()), destination));
                    }
                }
            }

        }

        return possibleMoves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pawn)) return false;
        Pawn pawn = (Pawn) o;
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
        return "P";
    }
}
