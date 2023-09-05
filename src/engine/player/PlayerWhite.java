package engine.player;

import engine.Team;
import engine.board.Board;
import engine.board.Move;
import engine.pieces.King;
import engine.pieces.Piece;

import java.util.List;

public class PlayerWhite extends Player {
    public PlayerWhite(Board board, Team team) {
        super(board, team);
        this.playerKing = getPlayerKing();
        this.activePieces = getActivePieces();
        updatePossibleMoves();
    }

    @Override
    public List<Piece> getActivePieces() {
        return this.board.getActivePieces(Team.WHITE);
    }

    @Override
    public List<Move> getOpponentMoves() {
        return this.board.getPossibleMoves(this.board.getActiveBlackPieces());
    }
}
