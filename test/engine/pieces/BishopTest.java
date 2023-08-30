package engine.pieces;

import engine.Team;
import engine.board.Board;
import engine.board.Move;
import engine.util.Position;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class BishopTest {
    @Test
    public void testCalculateMoves() {
        Board board = new Board();
        Piece bishop = new Bishop(new Position(7,7), Team.WHITE);
        List<Move> ret = bishop.calculateMoves(board);
        bishop = new Bishop(new Position(3,3), Team.WHITE);
    }
}
