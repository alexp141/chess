package engine.pieces;

import engine.Team;
import engine.board.Board;
import engine.board.Move;
import engine.util.Position;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RookTest {

    @Test
    public void testCalculateMoves() {
        Board board = new Board();
        Piece rook = new Rook(new Position(0,0), Team.WHITE);
        List<Move> ret = rook.calculateMoves(board);
        assertTrue(!ret.isEmpty());
    }
}