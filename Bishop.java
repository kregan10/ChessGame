package bcit.ca.comp2526.ChessGame;

import java.io.Serializable;

/**
 * Class for Bishop pieces, inherits all methods from ChessPiece.
 * 
 * @author Kerry Regan
 * @version 1.0
 */

public class Bishop extends ChessPiece implements Serializable {

    /**
     * Removing warning.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Bishop, calles ChessPiece constructor with specified file
     * path and name.
     * 
     * @param name
     *            name of piece
     * @param color
     *            color of piece
     * @param path
     *            filepath of the ImageIcon
     */
    public Bishop(final String name, final boolean color, final String path) {
        super(name, color, path);
    }

    /**
     * Makes sure the user has entered a valid move, can only move in 
     * diagonal directions.
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
    boolean isMoveValid(final int startX, final int startY, 
            final int endX, final int endY) {
        int diffX = Math.abs(endX - startX);
        int diffY = Math.abs(endY - startY);
        return diffX == diffY;
    }
    
    /**
     * Tests if the path is clear in diagonals.
     * 
     * @param startX
     *            origin x
     * @param startY
     *            origin y
     * @param endX
     *            selectedSquare x
     * @param endY
     *            selectedSquare y
     * @param chessSquares
     *            takes in array of chess squares to be incremented/decremented
     *            through.
     * @return True or false if the move is valid.
     */
    boolean isPathClear(final int startX, final int startY, final int endX,
            final int endY, ChessSquare[][] chessSquares) {
        
        final boolean upRight = (endX > startX) && (endY < startY);
        final boolean downRight = (endX > startX) && (endY > startY);
        final boolean upLeft = (endX < startX) && (endY < startY);
        final boolean downLeft = (endX < startX) && (endY > startY);
        boolean isPathClear = false;
        
        if (upRight) {
            isPathClear = upRightPathClear(startX, startY,
                    endX, endY, chessSquares);
        } else if (downRight) {
            isPathClear = downRightPathClear(startX, startY,
                    endX, endY, chessSquares);
        } else if (upLeft) {
            isPathClear = upLeftPathClear(startX, startY,
                    endX, endY, chessSquares);
        } else if (downLeft) {
            isPathClear = downLeftPathClear(startX, startY,
                    endX, endY, chessSquares);
        }
        return isPathClear;
    }
}
