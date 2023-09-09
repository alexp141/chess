package gui;

import engine.board.*;
import engine.pieces.King;
import engine.pieces.Rook;
import engine.util.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ChessGUI {

    private Board board;
    private JFrame mainFrame;
    private final Dimension CELL_PANEL_DIM = new Dimension(84, 84);
    private final String PIECE_PREFIX_PATH = "src/assets/pieces/";
    private BoardPanel boardPanel;
    private CellPanel primarySelection;
    private CellPanel secondarySelection;

    
    public ChessGUI() {
        this.mainFrame = new JFrame();
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setTitle("Java Chess");
        this.mainFrame.setLayout(new BorderLayout());
        ImageIcon mainFrameIcon = new ImageIcon("assets/w_pawn_1x_ns.png");
        this.mainFrame.setIconImage(mainFrameIcon.getImage());

        this.board = new Board();
        this.primarySelection = null;
        this.secondarySelection = null;
        this.boardPanel = new BoardPanel();

        this.mainFrame.add(boardPanel);
        this.mainFrame.pack();
        this.mainFrame.setVisible(true);
    }

    private class BoardPanel extends JPanel {

        private CellPanel[][] cells;

        BoardPanel() {
            this.setLayout(new GridLayout(8,8,0,0));
            this.cells = createCellGrid();
            
        }

        private CellPanel[][] createCellGrid() {
            CellPanel[][] cells = new CellPanel[8][8];

            boolean isLight = true;
            for (int i = 0; i < Board.BOARD_MAX_ROWS; i++) {
                for (int j = 0; j < Board.BOARD_MAX_COLS; j++) {
                    CellPanel cPanel = new CellPanel(isLight, new Position(j,i));
                    cells[i][j] = cPanel;
                    this.add(cPanel);
                    isLight = !isLight;
                }
                isLight = !isLight;
            }
            return cells;
        }

        public CellPanel[][] getCellPanels() {
            return this.cells;
        }

        public void refreshBoard() {
            for (int i = 0; i < Board.BOARD_MAX_ROWS; i++) {
                for (int j = 0; j < Board.BOARD_MAX_COLS; j++) {
                    cells[i][j].refreshCell();
                }
            }
            repaint();
        }
    }
    
    private class CellPanel extends JPanel {
        private Position position;
        private boolean isLight;
        private JLabel label;

        CellPanel(boolean isLight, Position position) {
            this.position = position;
            this.isLight = isLight;
            this.setLayout(new GridBagLayout());
            this.setPreferredSize(CELL_PANEL_DIM);
            this.setBackground(this.isLight ? Color.WHITE : Color.BLACK);
            createPieceLabel();
            addMouseListener(new ClickHandler());

        }

        private void createPieceLabel() {
            Cell cell = board.getCellAt(this.position.getX(), this.position.getY());
            if (cell instanceof OccupiedCell) {
                String pieceName = cell.getPiece().getPieceFilename();
                label = new JLabel();
                try {
                    BufferedImage img = ImageIO.read(new File(PIECE_PREFIX_PATH + pieceName)); //read image from file
                    Image scaledImg = img.getScaledInstance(64, 64, Image.SCALE_SMOOTH); //scaling the image down to preferred width x height
                    ImageIcon imgIcon = new ImageIcon(scaledImg); //converting to ImageIcon
                    label.setIcon(imgIcon);
                    add(label);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        protected Position getPosition() {
            return this.position;
        }

        protected Cell getBoardCell() {
            return board.getCellAt(position);
        }

        private void refreshCell() {
            this.removeAll();
            createPieceLabel();
            validate();
        }

        private class ClickHandler implements MouseListener {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (primarySelection == null) {
                    primarySelection = (CellPanel) e.getSource();
                    Cell cell = board.getCellAt(primarySelection.getPosition());
                    if (cell instanceof EmptyCell) {
                        primarySelection = null;
                        System.out.println("PS = null");
                    }
                    else {
                        if (cell.getPiece().getTeam() == board.getCurrentPlayer().getPlayerTeam()) {
                            primarySelection = (CellPanel) e.getSource();
                            System.out.println("PS = new cell");
                        }
                    }
                }
                else {
                    secondarySelection = (CellPanel) e.getSource();
                    Cell cell = board.getCellAt(secondarySelection.getPosition());
                    if (secondarySelection.getBoardCell().getPiece() instanceof Rook && primarySelection.getBoardCell().getPiece() instanceof King) {
                        System.out.println("SS set");
                        Move move = board.getCurrentPlayer().getMove(primarySelection.getPosition(), secondarySelection.getPosition());
                        if (move != null) {
                            board.getCurrentPlayer().executeMove(move);
                        }
                        primarySelection = null;
                        System.out.println("PS = null");
                        secondarySelection = null;
                        System.out.println("SS = null");
                        boardPanel.refreshBoard();
                    }
                    if (!(cell instanceof EmptyCell) && cell.getPiece().getTeam() == primarySelection.getBoardCell().getPiece().getTeam()) {
                        secondarySelection = null;
                    }
                    else {
                        System.out.println("SS set");
                        Move move = board.getCurrentPlayer().getMove(primarySelection.getPosition(), secondarySelection.getPosition());
                        if (move != null) {
                            board.getCurrentPlayer().executeMove(move);
                        }
                        primarySelection = null;
                        System.out.println("PS = null");
                        secondarySelection = null;
                        System.out.println("SS = null");
                        boardPanel.refreshBoard();


                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        }
    }
}
