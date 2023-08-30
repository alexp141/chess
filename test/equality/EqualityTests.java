package equality;

import engine.Team;
import engine.board.Board;
import engine.board.Move;
import engine.pieces.Bishop;
import engine.pieces.King;
import engine.pieces.Pawn;
import engine.pieces.Piece;
import engine.util.Position;
import org.junit.Test;

import java.util.List;
import static org.junit.Assert.*;
public class EqualityTests {
    @Test
    public void testPosition() {
        Position p1 = new Position(0,0);
        Position p2 = new Position(0,0);
        Position p3 = new Position(1,2);

        assertTrue(p1.equals(p2));
        assertTrue(p2.equals(p1));
        assertTrue(!p1.equals(p3));
        assertTrue(!p2.equals(p3));
        assertTrue(!p3.equals(p1));

        assertTrue(p1.hashCode() == p2.hashCode());
    }

    @Test
    public void testBishop() {
        Position p1 = new Position(0,0);
        Position p2 = new Position(0,0);
        Position p3 = new Position(1,2);

        Piece b1 = new Bishop(p1, Team.WHITE);
        Piece b2 = new Bishop(p1, Team.BLACK);
        Piece b3 = new Bishop(new Position(0,0), Team.WHITE);
        assertTrue(!b1.equals(b2));
        assertTrue(b1.equals(b3));
        assertTrue(b1.equals(b1));

        assertTrue(b1.hashCode() != b2.hashCode());
        assertTrue(b1.hashCode() == b3.hashCode());
    }

    @Test
    public void testKing() {
        Position p1 = new Position(0,0);
        Position p2 = new Position(0,0);
        Position p3 = new Position(1,2);

        Piece k1 = new King(p1, Team.WHITE);
        Piece k2 = new King(p1, Team.BLACK);
        Piece k3 = new King(new Position(0,0), Team.WHITE);
        assertTrue(!k1.equals(k2));
        assertTrue(k1.equals(k3));
        assertTrue(k1.equals(k1));
    }

    @Test
    public void testPawn() {
        Position p1 = new Position(0,0);
        Position p2 = new Position(0,0);
        Position p3 = new Position(1,2);

        Piece pawn1 = new Pawn(p1, Team.WHITE);
        Piece pawn2 = new Pawn(p1, Team.BLACK);
        Piece pawn3 = new Pawn(new Position(0,0), Team.WHITE);
        assertTrue(!pawn1.equals(pawn2));
        assertTrue(pawn1.equals(pawn3));
        assertTrue(pawn1.equals(pawn1));
    }


}
