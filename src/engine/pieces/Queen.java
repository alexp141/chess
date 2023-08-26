package engine.pieces;

import engine.Team;
import engine.board.Board;
import engine.board.Move;
import engine.util.Position;

import java.util.List;

public class Queen extends Piece {
    public Queen(Position position, Team team) {
        super(position, team);
    }

    @Override
    public List<Move> calculateMoves(Board board) {
        return null;
    }
}
