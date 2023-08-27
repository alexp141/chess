package engine.util;

import engine.board.*;
import engine.pieces.Piece;

import java.util.List;

public class Utility {
    //private constructor since it is a utility class
    private Utility() {

    }

    public static boolean checkIfInBounds(Position curr, int newX, int newY) {
        if(curr.getX() + newX >= 0 && curr.getX() + newX <= 7 &&
                curr.getY() + newY >= 0 && curr.getY() + newY <= 7) {
            return true;
        }
        return false;
    }

    public static boolean checkIfInBounds(Position curr, Position destination) {
        if(curr.getX() + destination.getX() >= 0 && curr.getX() + destination.getX() <= 7 &&
                curr.getY() + destination.getY() >= 0 && curr.getY() + destination.getY() <= 7) {
            return true;
        }
        return false;
    }

    public static boolean checkIfInBounds(int newX, int newY) {
        if(newX >= 0 && newX < Board.BOARD_MAX_COLS && newY >= 0 && newY < Board.BOARD_MAX_ROWS) {
            return true;
        }
        return false;
    }

    /**
     * calculates move type that a piece can do at the (x,y) coordinate
     * assuming that is a space the piece can legally move to
     * @param board
     * @param piece
     * @param x
     * @param y
     * @param possibleMoves
     */
    public static void calculateMoveType(Board board, Piece piece, int x, int y, List<Move> possibleMoves) {
        Cell destinationCell = board.getCellAt(x, y);
        if (destinationCell.isOccupied()) {
            Piece pieceAtDestination = destinationCell.getPiece();
            if (pieceAtDestination.getTeam() != piece.getTeam()) {
                possibleMoves.add(new AttackMove(board, piece, new Position(x, y), pieceAtDestination));
            }
        }
        else {
            possibleMoves.add(new PassiveMove(board, piece, new Position(x, y)));
        }
    }
}
