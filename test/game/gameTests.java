package game;

import engine.board.Board;
import engine.board.Move;
import engine.board.MoveStatus;
import engine.board.PassiveMove;
import engine.pieces.Pawn;
import engine.pieces.Piece;
import engine.player.Player;
import engine.util.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class gameTests {

    @Test
    public void gameTest1() {
        Board board = new Board();
        Player p = board.getCurrentPlayer();
        Piece wp4 = board.getPieceAt(3,6);
        Piece bp3 = board.getPieceAt(2,1);
        Piece wp5 = board.getPieceAt(4,6);
        Piece bq = board.getPieceAt(3,0);
        Piece wk = board.getPieceAt(4,7);

        assertTrue(board.getWhitePossibleMoves().size() == 20);
        assertTrue(board.getBlackPossibleMoves().size() == 20);
        Position wp4_dest = new Position(wp4.getPosition().getX(), wp4.getPosition().getY() - 2);
        Position bp3_dest = new Position(bp3.getPosition().getX(), bp3.getPosition().getY() + 2);
        Position wp5_dest = new Position(wp5.getPosition().getX(), wp5.getPosition().getY() - 2);
        Position bq_dest = new Position(bq.getPosition().getX() - 3, bq.getPosition().getY() + 3);
        Position wk_dest = new Position(wk.getPosition().getX(), wk.getPosition().getY() - 1);

        Move m1 = new PassiveMove(board, wp4, wp4.getPosition(), wp4_dest);
        assertTrue(p.executeMove(m1) == MoveStatus.LEGAL_MOVE);
        assertTrue(!board.getCellAt(3, 6).isOccupied());
        assertTrue(board.getCellAt(wp4_dest).isOccupied());

        p = board.getCurrentPlayer();

        Move m2 = new PassiveMove(board, bp3, bp3.getPosition(), bp3_dest);
        assertTrue(p.executeMove(m2) == MoveStatus.LEGAL_MOVE);
        assertTrue(!board.getCellAt(2, 1).isOccupied());
        assertTrue(board.getCellAt(bp3_dest).isOccupied());
        p = board.getCurrentPlayer();

        //assertTrue(board.getWhitePossibleMoves().size() == 26);

        Move m3 = new PassiveMove(board, wp5, wp5.getPosition(), wp5_dest);
        assertTrue(p.executeMove(m3) == MoveStatus.LEGAL_MOVE);
        assertTrue(!board.getCellAt(4, 6).isOccupied());
        assertTrue(board.getCellAt(wp5_dest).isOccupied());
        p = board.getCurrentPlayer();

        Move m4 = new PassiveMove(board, bq, bq.getPosition(), bq_dest);
        assertTrue(p.executeMove(m4) == MoveStatus.LEGAL_MOVE);
        assertTrue(!board.getCellAt(3, 0).isOccupied());
        assertTrue(board.getCellAt(bq_dest).isOccupied());
        p = board.getCurrentPlayer();

        Move m5 = new PassiveMove(board, wk, wk.getPosition(), wk_dest);
        assertTrue(p.executeMove(m5) == MoveStatus.ILLEGAL_MOVE);
        assertTrue(board.getCellAt(4, 7).isOccupied());
        assertTrue(!board.getCellAt(wk_dest).isOccupied());
        p = board.getCurrentPlayer();

    }
}
