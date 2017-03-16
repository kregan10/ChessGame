package bcit.ca.comp2526.ChessGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JPanel;

/**
 * This class sets up the ChessBoard, and puts the pieces on the appropriate
 * spots. This also allows the user to move the pieces.
 * 
 * @author Kerry Regan
 * @version 1.0
 */
public class ChessBoard extends JPanel implements Serializable {

    /**
     * To remove checkstyle.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Size of the board.
     */
    private static final int BOARD_SIZE = 8;

    /**
     * Turn for game.
     */
    private boolean turn;

    /**
     * Setting up the chessboard to hold the squares.
     */
    private JPanel chessBoard;

    /**
     * Setting up array of ChessSquares.
     */
    private ChessSquare[][] chessSquares;

    /**
     * Color for white squares.
     */
    private final boolean white = true;

    /**
     * Color for black squares.
     */
    private final boolean black = false;
    
    /**
     * Constant for middle of board.
     */
    private final int midOfBoard = 3;
    
    /**
     * Constructor for chessboard.
     */
    public ChessBoard() {

        turn = white;
        chessBoard = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        chessSquares = this.getChessSquares();

        for (int i = 0; i < chessSquares.length; i++) {
            for (int j = 0; j < chessSquares[i].length; j++) {
                setUpSquare(i, j);
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    chessSquares[i][j].setBackground(Color.WHITE);
                    chessSquares[i][j].setColor(Color.WHITE);
                } else {
                    chessSquares[i][j].setBackground(Color.GRAY);
                    chessSquares[i][j].setColor(Color.GRAY);
                }
                if (i == 0) {
                    if (j == 0 || j == 2 * midOfBoard + 1) {
                        putsPieceAtSquare(i, j, 
                                new Rook("blackRook", getBlack(),
                                        "Images/blackRook.png"));
                    } else if (j == 1 || j == 2 * midOfBoard) {
                        putsPieceAtSquare(i, j, 
                                new Knight("blackKnight", getBlack(),
                                        "Images/blackKnight.png"));
                    } else if (j == 2 || j == (midOfBoard + 2)) {
                        putsPieceAtSquare(i, j, 
                                new Bishop("blackBishop", getBlack(),
                                        "Images/blackBishop.png"));
                    } else if (j == midOfBoard) {
                        putsPieceAtSquare(i, j, 
                                new King("blackKing", getBlack(),
                                        "Images/blackKing.png"));
                    } else if (j == midOfBoard + 1) {
                        putsPieceAtSquare(i, j, 
                                new Queen("blackQueen", getBlack(),
                                        "Images/blackQueen.png"));
                    }
                } else if (i == 1) {
                    putsPieceAtSquare(i, j, 
                            new Pawn("blackPawn", getBlack(),
                                    "Images/blackPawn.png"));
                } else if (i == 2 * midOfBoard) {
                    putsPieceAtSquare(i, j, 
                            new Pawn("whitePawn", getWhite(),
                                    "Images/whitePawn.png"));
                } else if (i == 2 * midOfBoard + 1) {

                    if (j == 0 || j == 2 * midOfBoard + 1) {
                        putsPieceAtSquare(i, j, 
                                new Rook("whiteRook", getWhite(),
                                        "Images/whiteRook.png"));
                    } else if (j == 1 || j == 2 * midOfBoard) {
                        putsPieceAtSquare(i, j, 
                                new Knight("whiteKnight", getWhite(),
                                        "Images/whiteKnight.png"));
                    } else if (j == 2 || j == midOfBoard + 2) {
                        putsPieceAtSquare(i, j, 
                                new Bishop("whiteBishop", getWhite(),
                                        "Images/whiteBishop.png"));
                    } else if (j == midOfBoard) {
                        putsPieceAtSquare(i, j, 
                                new Queen("whiteQueen", getWhite(),
                                        "Images/whiteQueen.png"));
                    } else if (j == midOfBoard + 1) {
                        putsPieceAtSquare(i, j, 
                                new King("whiteKing", getWhite(),
                                        "Images/whiteKing.png"));
                    }
                }
                chessBoard.add(chessSquares[i][j]);
            }
        }
        this.add(chessBoard);
    }


    /**
     * Returns this object's chess board.
     * 
     * @return chessSquares chessSquares
     */
    ChessSquare[][] getBoard() {
        return chessSquares;
    }

    /**
     * Getting white boolean value.
     * 
     * @return white boolean value for white.
     */
    private boolean getWhite() {
        return white;
    }

    /**
     * Getting black boolean value.
     * 
     * @return black boolean value for black.
     */
    private boolean getBlack() {
        return black;
    }

    /**
     * Switching turns from black to white, or white to black.
     */
    private void switchTurns() {
        if (turn == white) {
            turn = black;
        } else {
            turn = white;
        }
    }

    /**
     * Switching turns from black to white, or white to black.
     * 
     * @return black or white boolean value for black or white.
     */
    public CustomListener getListener() {
        return new CustomListener();
    }

    /**
     * Getting board size.
     * 
     * @return the size of the chessboard.
     */
    private int getBoardSize() {
        return BOARD_SIZE;
    }

    /**
     * Getting array of ChessSquares.
     * 
     * @return chessSquare[][] 2-dimensional chessSquare array
     */
    private ChessSquare[][] getChessSquares() {
        return new ChessSquare[getBoardSize()][getBoardSize()];
    }

    /**
     * Adds listeners to chess squares.
     * 
     * @param square
     *            Square to add listener to.
     */
    public void addListeners(final ChessSquare square) {
        square.addActionListener(new CustomListener());
    }

    /**
     * Searches through whole board to find the one piece which is selected.
     * 
     * @return Selected Piece
     */
    private ChessSquare findSelectedSquare() {
        int i = 0;
        int j = 0;
        ChessSquare selectedPiece = null;
        for (i = 0; i < chessSquares.length; i++) {
            for (j = 0; j < chessSquares[i].length; j++) {
                ChessPiece currentPiece = chessSquares[i][j].occupiedBy();
                if (currentPiece == null) {
                    continue;
                } else if (currentPiece.isSelected()) {
                    selectedPiece = chessSquares[i][j];
                    break;
                }
            }
        }
        return selectedPiece;
    }

    /**
     * Places piece at chessSquare.
     * 
     * @param i
     *          x coordinates of chess square
     * @param j
     *          y coordinates of chess square
     * @param piece
     *          Piece to be placed at chesssquare
     */
    private void putsPieceAtSquare(final int i, 
            final int j, final ChessPiece piece) {
        chessSquares[i][j].occupy(piece);
        chessBoard.add(chessSquares[i][j]);
    }
    
    /**
     * Sets up chess square.
     * 
     * @param i
     *          x coordinates of chess square
     * @param j
     *          y coordinates of chess square
     */
    private void setUpSquare(final int i, final int j) {
        chessSquares[i][j] = 
                new ChessSquare(i * ChessSquare.SQUARE_SIDE, 
                        j * ChessSquare.SQUARE_SIDE);
        chessSquares[i][j].setPreferredSize(
                new Dimension(ChessSquare.SQUARE_SIDE, 
                        ChessSquare.SQUARE_SIDE));
        chessSquares[i][j].setOpaque(true);
        chessSquares[i][j].setBorderPainted(false);
        chessSquares[i][j].addActionListener(new CustomListener());
    }

    
    /**
     * Listener class for each square.
     * 
     * @author Kerry Regan
     */
    class CustomListener implements ActionListener, Serializable {
        
        /**
         * Added to remove checkstyle error.
         */
        private static final long serialVersionUID = 1L;

        /**
         * Moves a piece from the origin square to the just clicked on square.
         * and switches turns
         * 
         * @param origin
         *            origin, old Square
         * @param currentSquare
         *            currentSquare chessPiece at new square
         * 
         * @param originPiece
         *            originPiece chessPeice at old square.
         */
        private void movePiece(final ChessSquare origin, 
                final ChessSquare currentSquare, 
                final ChessPiece originPiece) {
            originPiece.increaseNumOfMoves();
            origin.vacate(); // removing piece from origin
            originPiece.deselectPiece();
            origin.setColorToDefault();
            currentSquare.occupy(originPiece); // putting piece
            switchTurns();
        }

        /**
         * Moves a piece from the origin square to the just clicked on square.
         * Also turns the origin square cyan once clicked.
         * 
         * @param e
         *          event
         */
        public void actionPerformed(final ActionEvent e) {

            /*
             * Square that was just clicked on.
             */
            ChessSquare currentSquare = (ChessSquare) e.getSource();
            ChessPiece currentPiece = null;
            ChessSquare origin = null;
            ChessPiece originPiece = null;

            try {

                /*
                 * If no pieces have been selected yet, make this one the
                 * selection.
                 */
                if (findSelectedSquare() == null) {
                    currentPiece = currentSquare.occupiedBy();
                    if (currentPiece.getColor() == turn) {
                        currentPiece.selectPiece();
                        currentSquare.setBackground(Color.CYAN);
                    }
                } else {
                    try {
                        /*
                         * Finds the piece that was selected last click, and
                         * moves the piece from the origin to currentSquare.
                         */
                        origin = findSelectedSquare();
                        originPiece = origin.occupiedBy();
                        currentPiece = currentSquare.occupiedBy();

                        /*
                         * if user clicks on the same piece twice, deselect
                         * piece.
                         */
                        if (currentPiece == originPiece) {
                            originPiece.deselectPiece();
                            origin.setColorToDefault();
                        }

                        final int startX = origin.getXLoc();
                        final int startY = origin.getYLoc();
                        final int endX = currentSquare.getXLoc();
                        final int endY = currentSquare.getYLoc();

                        final boolean isAttack = currentPiece != null
                                && (originPiece.getColor()
                                        != currentPiece.getColor());
                        final boolean isPathClear 
                        = originPiece.isPathClear(startX, startY, 
                                endX, endY, chessSquares);

                        boolean isValidMove = false;
                        if (isAttack) {
                            isValidMove 
                            = originPiece.isAttackMoveValid(startX,
                                    startY, endX, endY);
                        } else {
                            isValidMove 
                            = originPiece.isMoveValid(startX,
                                startY, endX, endY) 
                                && ((currentPiece == null) 
                                || currentPiece.getColor()
                                != originPiece.getColor());
                        }

                        if (isValidMove && isPathClear) {
                            movePiece(origin, currentSquare, originPiece);
                        }

                    } catch (NullPointerException nul) {
                        System.out.println();
                    }

                }

            } catch (NullPointerException nul) {
                System.out.println();
            }

        }
    }
}
