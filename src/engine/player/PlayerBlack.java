package engine.player;

import engine.Team;
import engine.board.Board;
import engine.board.Move;
import engine.pieces.King;
import engine.pieces.Piece;

import java.util.List;

public class PlayerBlack extends Player {
    public PlayerBlack(Board board, Team team, List<Move> blackPossibleMoves) {
        super(board, team, blackPossibleMoves);
        this.playerKing = getPlayerKing();
        this.activePieces = getActivePieces();
        this.possibleMoves = blackPossibleMoves;
    }

    @Override
    public List<Piece> getActivePieces() {
        return this.board.getActivePieces(Team.BLACK);
    }

}
