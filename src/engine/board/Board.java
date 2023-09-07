package engine.board;

import engine.Team;
import engine.pieces.*;
import engine.player.Player;
import engine.player.PlayerBlack;
import engine.player.PlayerWhite;
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

    private Player playerWhite;
    private Player playerBlack;
    private Player currentPlayer;
    private Player winner;
    private boolean isGameOver;


    public Board() {
        this.board = initBoard();
        this.activeWhitePieces = getActivePieces(Team.WHITE);
        this.activeBlackPieces = getActivePieces(Team.BLACK);
        this.whitePossibleMoves = new ArrayList<>();
        this.blackPossibleMoves = new ArrayList<>();
        updatePossibleMovesWhite();
        updatePossibleMovesBlack();
        this.playerWhite = new PlayerWhite(this, Team.WHITE, this.whitePossibleMoves);
        this.playerBlack = new PlayerBlack(this, Team.BLACK, this.blackPossibleMoves);
        this.currentPlayer = this.playerWhite;
        this.winner = null;
        this.isGameOver = false;
    }

    /**
     * calculates move type that a piece can do at the (x,y) coordinate
     * assuming that is a space the piece can legally move to
     * @param board
     * @param piece
     * @param x
     * @param y
     * @param possibleMoves
     * @return returns true if cell is empty and if checkLine() can continue using this method in its calculation
     */
    public static boolean calculateMoveType(Board board, Piece piece, int x, int y, List<Move> possibleMoves) {
        Cell destinationCell = board.getCellAt(x, y);
        if (destinationCell.isOccupied()) {
            Piece pieceAtDestination = destinationCell.getPiece();
            if (pieceAtDestination.getTeam() != piece.getTeam()) {
                possibleMoves.add(new AttackMove(board, piece, piece.getPosition(), new Position(x, y), pieceAtDestination));
            }
            return false;

        }
        else {
            possibleMoves.add(new PassiveMove(board, piece, piece.getPosition(), new Position(x, y)));
            return true;
        }
    }
/*
    public void refreshMoveSet() {
        this.activeWhitePieces = getActivePieces(Team.WHITE);
        this.activeBlackPieces = getActivePieces(Team.BLACK);
        this.whitePossibleMoves = getPossibleMoves(this.activeWhitePieces);
        this.blackPossibleMoves = getPossibleMoves(this.activeBlackPieces);
        this.legalMoves = this.currentPlayer.getPossibleMoves();
    }
*/
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

        for (int i = 2; i < BOARD_MAX_ROWS-2; i++) {
            for (int j = 0; j < BOARD_MAX_COLS; j++) {
                board[i][j] = new EmptyCell();
            }
        }

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

    public List<Move> getWhitePossibleMoves() {
        return this.whitePossibleMoves;
    }

    public List<Move> getBlackPossibleMoves() {
        return this.blackPossibleMoves;
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

    public void removePiece(int x, int y) {
        this.board[y][x] = new EmptyCell();
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
                if (piece != null && piece.getTeam() == team) {
                    pieces.add(piece);
                }
            }
        }

        return pieces;
    }

    public void updatePossibleMovesWhite() {
        this.whitePossibleMoves.clear();

        for (Piece piece : this.activeWhitePieces) {
           this.whitePossibleMoves.addAll(piece.calculateMoves(this));
        }

    }

    public void updatePossibleMovesBlack() {
        this.blackPossibleMoves.clear();

        for (Piece piece : this.activeBlackPieces) {
            this.blackPossibleMoves.addAll(piece.calculateMoves(this));
        }

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

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void switchPlayers() {
        if (this.currentPlayer.getPlayerTeam() == Team.WHITE) {
            this.currentPlayer = this.playerBlack;
        }
        else {
            this.currentPlayer = this.playerWhite;
        }

    }
}
