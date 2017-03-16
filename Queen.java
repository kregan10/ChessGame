package bcit.ca.comp2526.ChessGame;

import java.io.Serializable;

/**
 * Class for Queen pieces, inherits all methods from ChessPiece.
 * 
 * @author Kerry Regan
 * @version 1.0
 */
public class Queen extends ChessPiece implements Serializable {
    
    /**
     * To remove warning.
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
    public Queen(final String name, final boolean color, final String path) {
        super(name, color, path);
    }
    
    

    /**
     * Tests the validity of movement.  Can move in any direction.
     * 
     * @param startX
     *            origin x
     * @param startY
     *            origin y
     * @param endX
     *            selectedSquare x
     * @param endY
     *            selectedSquare y
     * @return True or false if the move is invalid
     */
    boolean isMoveValid(final int startX, final int startY, final int endX, 
            final int endY) {
        int diffX = Math.abs(endX - startX);
        int diffY = Math.abs(endY - startY);
        return (startX == endX || startY == endY) || diffX == diffY;
    }

    /**
     * Tests if the path is clear.
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
     *             through.
     * @return True or false if the square the piece is moving to either is not
     *  occupied,
     *         or is occupied by opposing team
     */
    boolean isPathClear(final int startX, final int startY, final int endX, 
            final int endY, final ChessSquare[][] chessSquares) {

        final boolean up = endY < startY;
        final boolean down = endY > startY;
        final boolean left = endX < startX;
        final boolean right = endX > startX;
        final boolean upRight = (endX > startX) && (endY < startY);
        final boolean downRight = (endX > startX) && (endY > startY);
        final boolean upLeft = (endX < startX) && (endY < startY);
        final boolean downLeft = (endX < startX) && (endY > startY);
        boolean isPathClear = false;

        if (up) {
            isPathClear = upPathClear(startX, startY, endX, endY, chessSquares);
        } else if (down) {
            isPathClear = downPathClear(startX, startY, endX, endY,
                    chessSquares);
        } else if (left) {
            isPathClear = leftPathClear(startX, startY, endX, endY,
                    chessSquares);
        } else if (right) {
            isPathClear = rightPathClear(startX, startY, endX, endY,
                    chessSquares);
        } else if (upRight) {
            isPathClear = upRightPathClear(startX, startY, endX, endY,
                    chessSquares);
        } else if (downRight) {
            isPathClear = downRightPathClear(startX, startY, endX, endY,
                    chessSquares);
        } else if (upLeft) {
            isPathClear = upLeftPathClear(startX, startY, endX, endY,
                    chessSquares);
        } else if (downLeft) {
            isPathClear = downLeftPathClear(startX, startY, endX, endY,
                    chessSquares);
        }
        return isPathClear;
    }
}
    
    
