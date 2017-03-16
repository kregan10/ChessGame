package bcit.ca.comp2526.ChessGame;

import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Main class for all ChessPieces.
 * 
 * @author Kerry Regan
 * @version 1.0
 */
abstract class ChessPiece extends JLabel implements Serializable {

    /**
     * Lets the ChessBoard know if there has been a piece selected.
     */
    static boolean isAPieceSelected;
    
    /**
     * To remove checkstyle error.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Pieces's selected status.
     */
    private boolean isSelected;
    
    /**
     * Pieces' color.
     */
    private boolean color;
    
    /**
     * Setting up color for white pieces.
     */
    private final boolean white = true;
    
    /**
     * Setting up color for black pieces.
     */
    private final boolean black = false;
    
    /**
     * Number of moves for which the piece has taken.
     */
    private int numOfMoves;
    
    /**
     * Icon for ChessPiece.
     */
    private ImageIcon icon;
    
    
    /**
     * Sets up chess piece, and assigns name, color, and file path of the icon.
     * 
     * @param name
     *            Name of ChessPiece
     * @param color
     *            Color of ChessPiece
     * @param path
     *            File path of Icon
     */
     ChessPiece(String name, boolean color, String path) {
        this.setName(name);
        this.setColor(color);
        this.isSelected = false;
        this.icon = createImageIcon(path, name);
        this.numOfMoves = 0;
    }
    
    /**
     * Returns the icon of the piece.
     * 
     * @retun icon 
     *          icon of this piece
     */
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    /**
     * Sets the icon of the piece.
     * @param icon
     *          icon of this piece
     */
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    /**
     * Counter for a piece's number of moves.
     */
    void increaseNumOfMoves() {
        this.numOfMoves++;
    }
    /**
     * Returns the number of moves taken by piece.
     * 
     * @return number of moves taken by piece
     */
    int getNumOfMoves() {
        return this.numOfMoves;
    }

    /**
     * Returns the color of the ChessPiece.
     * 
     * @return color color of ChessPiece
     */
    boolean getColor() {
        return color;
    }
    
    /**
     * Gets the white value, which is true.
     * 
     * @return white
     *          boolean value of white
     */
    boolean getWhite() {
        return white;
    }
    
    /**
     * Gets the black value, which is false.
     * 
     * @return black
     *          boolean value of black
     */
    boolean getBlack() {
        return black;
    }

    /**
     * Returns the selection status of the ChessPiece.
     * 
     * @return isSelected 
     *              Selection status of ChessPiece
     */
    boolean isSelected() {
        return isSelected;
    }

    /**
     * Selects the current piece, and sets static variable isAPieceSelected to
     * true.
     */
    void selectPiece() {
        isAPieceSelected = true;
        this.isSelected = true;
    }

    /**
     * Deselects the current piece, and sets static variable isAPieceSelected to
     * false.
     */
    void deselectPiece() {
        isAPieceSelected = false;
        this.isSelected = false;
    }

    /**
     * Used by the constructor to set the piece color.
     * 
     * @param pieceColor
     *          white is true, black is false.
     */
    private void setColor(final boolean pieceColor) {
        if (pieceColor == white) {
            this.color = white;
        } else {
            this.color = black;
        }
    }

    /**
     * Checks to see if the attack move is valid.  This is really just for
     * the pawn's attack moves.
     * 
     * @param startX
     *            origin x
     * @param startY
     *            origin y
     * @param endX
     *            selectedSquare's end x position
     * @param endY
     *            selectedSquare's end y position
     * @return True or false if the attack move is valid
     */
    boolean isAttackMoveValid(final int startX, final int startY, 
            final int endX, final int endY) {
        return isMoveValid(startX, startY, endX, endY);
    }

    /**
     * Checks to see if the up path is clear.
     * 
     * @param startX
     *            origin x
     * @param startY
     *            origin y
     * @param endX
     *            selectedSquare's end x position
     * @param endY
     *            selectedSquare's end y position
     * @param chessSquares
     *            array of chessSquares to be iterated through.
     * @return True or false if the move is valid
     */
    protected boolean upPathClear(final int startX, final int startY, 
            final int endX, final int endY, 
            final ChessSquare[][] chessSquares) {

        int currentX = startX;
        int currentY = startY;
        currentY--;
        while (currentY > endY) {
            if (chessSquares[currentY][currentX].isOccupied()) {
                return false;
            }
            currentY--;
        }
        return true;
    }

    /**
     * Checks to see if the down path is clear.
     * 
     * @param startX
     *            origin x
     * @param startY
     *            origin y
     * @param endX
     *            selectedSquare's end x position
     * @param endY
     *            selectedSquare's end y position
     * @param chessSquares
     *            array of chessSquares to be iterated through.
     * @return True or false if the move is valid
     */
    protected boolean downPathClear(final int startX, final int startY, 
            final int endX, final int endY, 
            final ChessSquare[][] chessSquares) {

        int currentX = startX;
        int currentY = startY;
        currentY++;
        while (currentY < endY) {
            if (chessSquares[currentY][currentX].isOccupied()) {
                return false;
            }
            currentY++;
        }
        return true;
    }

    /**
     * Checks to see if the left path is clear.
     * 
     * @param startX
     *            origin x
     * @param startY
     *            origin y
     * @param endX
     *            selectedSquare's end x position
     * @param endY
     *            selectedSquare's end y position
     * @param chessSquares
     *            array of chessSquares to be iterated through.
     * @return True or false if the move is valid
     */
    protected boolean leftPathClear(final int startX, final int startY, 
            final int endX, final int endY, 
            final ChessSquare[][] chessSquares) {

        int currentX = startX;
        int currentY = startY;
        currentX--;
        while (currentX > endX) {
            if (chessSquares[currentY][currentX].isOccupied()) {
                return false;
            }
            currentX--;
        }
        return true;
    }

    /**
     * Checks to see if the right path is clear.
     * 
     * @param startX
     *            origin x
     * @param startY
     *            origin y
     * @param endX
     *            selectedSquare's end x position
     * @param endY
     *            selectedSquare's end y position
     * @param chessSquares
     *            array of chessSquares to be iterated through.
     * @return True or false if the move is valid
     */
    protected boolean rightPathClear(final int startX, final int startY, 
            final int endX, final int endY, 
            final ChessSquare[][] chessSquares) {

        int currentX = startX;
        int currentY = startY;
        currentX++;
        while (currentX < endX) {
            if (chessSquares[currentY][currentX].isOccupied()) {
                return false;
            }
            currentX++;
        }
        return true;
    }

    /**
     * Checks to see if the up right path is clear.
     * 
     * @param startX
     *            origin x
     * @param startY
     *            origin y
     * @param endX
     *            selectedSquare's end x position
     * @param endY
     *            selectedSquare's end y position
     * @param chessSquares
     *            array of chessSquares to be iterated through.
     * @return True or false if the move is valid
     */
    protected boolean upRightPathClear(final int startX, final int startY, 
            final int endX, final int endY, 
            final ChessSquare[][] chessSquares) {

        int currentX = startX;
        int currentY = startY;
        currentX++;
        currentY--;
        while (currentX < endX && currentY > endY) {
            if (chessSquares[currentY][currentX].isOccupied()) {
                return false;
            }
            currentX++;
            currentY--;
        }
        return true;
    }
    
    /**
     * Checks to see if the down right path is clear.
     * 
     * @param startX
     *            origin x
     * @param startY
     *            origin y
     * @param endX
     *            selectedSquare's end x position
     * @param endY
     *            selectedSquare's end y position
     * @param chessSquares
     *            array of chessSquares to be iterated through.
     * @return True or false if the move is valid
     */
    protected boolean downRightPathClear(final int startX, final int startY, 
            final int endX, final int endY, 
            final ChessSquare[][] chessSquares) {
        int currentX = startX;
        int currentY = startY;
        currentX++;
        currentY++;
        while (currentX < endX && currentY < endY) {
            if (chessSquares[currentY][currentX].isOccupied()) {
                return false;
            }
            currentX++;
            currentY++;
        }
        return true;
    }

    /**
     * Checks to see if the up left path is clear.
     * 
     * @param startX
     *            origin x
     * @param startY
     *            origin y
     * @param endX
     *            selectedSquare's end x position
     * @param endY
     *            selectedSquare's end y position
     * @param chessSquares
     *            array of chessSquares to be iterated through.
     * @return True or false if the move is valid
     */
    protected boolean upLeftPathClear(final int startX, final int startY, 
            final int endX, final int endY, 
            final ChessSquare[][] chessSquares) {

        int currentX = startX;
        int currentY = startY;
        currentX--;
        currentY--;
        while (currentX > endX && currentY > endY) {
            if (chessSquares[currentY][currentX].isOccupied()) {
                return false;
            }
            currentX--;
            currentY--;
        }
        return true;
    }

    /**
     * Checks to see if the down left path is clear.
     * 
     * @param startX
     *            origin x
     * @param startY
     *            origin y
     * @param endX
     *            selectedSquare's end x position
     * @param endY
     *            selectedSquare's end y position
     * @param chessSquares
     *            array of chessSquares to be iterated through.
     * @return True or false if the move is valid
     */
    protected boolean downLeftPathClear(final int startX, final int startY, 
            final int endX, final int endY, 
            final ChessSquare[][] chessSquares) {

        int currentX = startX;
        int currentY = startY;
        currentX--;
        currentY++;
        while (currentX > endX && currentY < endY) {
            if (chessSquares[currentY][currentX].isOccupied()) {
                return false;
            }
            currentX--;
            currentY++;
        }
        return true;
    }

    /**
     * Tests if the move is valid.
     * 
     * @param startX
     *            origin x
     * @param startY
     *            origin y
     * @param endX
     *            selectedSquare x
     * @param endY
     *            selectedSquare y
     * @return True or false if the move is valid
     */
    abstract boolean isMoveValid(final int startX, final int startY, 
            final int endX, final int endY);

    /**
     * Checks to see if the path is clear.
     * 
     * @param startX
     *            origin x
     * @param startY
     *            origin y
     * @param endX
     *            selectedSquare's end x position
     * @param endY
     *            selectedSquare's end y position
     * @param chessSquares
     *            array of chessSquares to be iterated through.
     * @return True or false if the move is valid
     */
    abstract boolean isPathClear(final int startX, final int startY, 
            final int endX, final int endY, final ChessSquare[][] chessSquares);

    /**
     * Sets up the Image Icon for the for current piece.
     * 
     * @param path
     *            Filepath
     * @param description
     *            description of file
     * @return Returns ImageIcon objest i
     */
    protected ImageIcon createImageIcon(final String path, 
            final String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
