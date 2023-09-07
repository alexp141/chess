package engine.player;

import engine.Team;
import engine.board.Board;
import engine.board.Move;
import engine.pieces.King;
import engine.pieces.Piece;

import java.util.List;

public class PlayerWhite extends Player {
    public PlayerWhite(Board board, Team team, List<Move> whitePossibleMoves) {
        super(board, team, whitePossibleMoves);
        this.playerKing = getPlayerKing();
        this.activePieces = getActivePieces();
        this.possibleMoves = whitePossibleMoves;
    }

    @Override
    public List<Piece> getActivePieces() {
        return this.board.getActivePieces(Team.WHITE);
    }

}
