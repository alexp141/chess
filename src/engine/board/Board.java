package engine.board;

import engine.Team;
import engine.pieces.*;
import engine.player.Player;
import engine.util.Position;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public static final int BOARD_MAX_ROWS = 8;
    public static final int BOARD_MAX_COLS = 8;
    public static final int BOARD_SECOND_ROW = 1;
    public static final int BOARD_FIRST_ROW = 0;
    public static final int BOARD_SEVENTH_ROW = 6;
    public static final int BOARD_LAST_ROW = 7;
    private Cell[][] board;
    private List<Piece> activeWhitePieces;
    private List<Piece> activeBlackPieces;

    private List<Move> whitePossibleMoves;
    private List<Move> blackPossibleMoves;
    private Player currentPlayer;

    public Board() {
        this.board = initBoard();
        this.currentPlayer = null;
        this.activeWhitePieces = getActivePieces(Team.WHITE);
        this.activeBlackPieces = getActivePieces(Team.BLACK);
        this.whitePossibleMoves = getPossibleMoves(this.activeWhitePieces);
        this.blackPossibleMoves = getPossibleMoves(this.activeBlackPieces);

    }

    private Cell[][] initBoard() {
        Cell[][] board = new Cell[8][8];
        //black pawns
        for (int i = 0; i < BOARD_MAX_COLS; i++) {
            board[BOARD_SECOND_ROW][i] = new OccupiedCell(new Pawn(new Position(i, BOARD_SECOND_ROW), Team.BLACK));
        }
        //white pawns
        for (int i = 0; i < BOARD_MAX_COLS; i++) {
            board[BOARD_SEVENTH_ROW][i] = new OccupiedCell(new Pawn(new Position(i, BOARD_SEVENTH_ROW), Team.WHITE));
        }

        board[BOARD_FIRST_ROW][0] = new OccupiedCell(new Rook(new Position(0, BOARD_FIRST_ROW), Team.BLACK));
        board[BOARD_FIRST_ROW][1] = new OccupiedCell(new Knight(new Position(1, BOARD_FIRST_ROW), Team.BLACK));
        board[BOARD_FIRST_ROW][2] = new OccupiedCell(new Bishop(new Position(2, BOARD_FIRST_ROW), Team.BLACK));
        board[BOARD_FIRST_ROW][3] = new OccupiedCell(new Queen(new Position(3, BOARD_FIRST_ROW), Team.BLACK));
        board[BOARD_FIRST_ROW][4] = new OccupiedCell(new King(new Position(4, BOARD_FIRST_ROW), Team.BLACK));
        board[BOARD_FIRST_ROW][5] = new OccupiedCell(new Bishop(new Position(5, BOARD_FIRST_ROW), Team.BLACK));
        board[BOARD_FIRST_ROW][6] = new OccupiedCell(new Knight(new Position(6, BOARD_FIRST_ROW), Team.BLACK));
        board[BOARD_FIRST_ROW][7] = new OccupiedCell(new Rook(new Position(7, BOARD_FIRST_ROW), Team.BLACK));

        board[BOARD_LAST_ROW][0] = new OccupiedCell(new Rook(new Position(0, BOARD_LAST_ROW), Team.WHITE));
        board[BOARD_LAST_ROW][1] = new OccupiedCell(new Knight(new Position(1, BOARD_LAST_ROW), Team.WHITE));
        board[BOARD_LAST_ROW][2] = new OccupiedCell(new Bishop(new Position(2, BOARD_LAST_ROW), Team.WHITE));
        board[BOARD_LAST_ROW][3] = new OccupiedCell(new Queen(new Position(3, BOARD_LAST_ROW), Team.WHITE));
        board[BOARD_LAST_ROW][4] = new OccupiedCell(new King(new Position(4, BOARD_LAST_ROW), Team.WHITE));
        board[BOARD_LAST_ROW][5] = new OccupiedCell(new Bishop(new Position(5, BOARD_LAST_ROW), Team.WHITE));
        board[BOARD_LAST_ROW][6] = new OccupiedCell(new Knight(new Position(6, BOARD_LAST_ROW), Team.WHITE));
        board[BOARD_LAST_ROW][7] = new OccupiedCell(new Rook(new Position(7, BOARD_LAST_ROW), Team.WHITE));


        return board;
    }

    public Cell[][] getBoard() {
        return this.board;
    }

    public List<Piece> getActiveWhitePieces() {
        return this.activeWhitePieces;
    }

    public List<Piece> getActiveBlackPieces() {
        return this.activeBlackPieces;
    }

    public Cell getCellAt(Position position) {
        return this.board[position.getY()][position.getX()];
    }

    public Cell getCellAt(int x, int y) {
        return this.board[y][x]; //[row][col]
    }

    public Piece getPieceAt(Position position) {
        return getCellAt(position).getPiece();
    }

    public Piece getPieceAt(int x, int y) {
        return this.board[y][x].getPiece();
    }

    public void placePiece(Piece piece, int x, int y) {
        this.board[y][x] = new OccupiedCell(piece);
    }

    /**
     * get active pieces for white or black
     * @param team
     * @return
     */
    public List<Piece> getActivePieces(Team team) {
        List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < BOARD_MAX_ROWS; i++) {
            for (int j = 0; j < BOARD_MAX_COLS; j++) {
                Piece piece = board[i][j].getPiece();
                if (piece.getTeam() == team) {
                    pieces.add(piece);
                }
            }
        }

        return pieces;
    }

    public List<Move> getPossibleMoves(List<Piece> teamPieces) {
        List<Move> possibleMoves = new ArrayList<>();

        for (Piece piece : teamPieces) {
            possibleMoves.addAll(piece.calculateMoves(this));
        }
        return possibleMoves;
    }

    /**
     * keeps track of which cells are being attacked by white or black pieces
     * @return
     */
    public Cell[][] updateCellData() {
        //get white moves
        //get black moves
        //calculate which cells are being attacked

        //reset board
        for (int i = 0; i < BOARD_MAX_ROWS; i++) {
            for (int j = 0; j < BOARD_MAX_COLS; j++) {
                board[i][j].setAttackedByBlack(false);
                board[i][j].setAttackedByWhite(false);
            }

        }

        //recalculate
        for (Move move : this.whitePossibleMoves) {
            getCellAt(move.getDestination()).setAttackedByWhite(true);
        }

        for (Move move : this.blackPossibleMoves) {
            getCellAt(move.getDestination()).setAttackedByBlack(true);
        }
        return this.board;
    }

    public Cell[][] copyBoard() {

        return null;
    }
}
