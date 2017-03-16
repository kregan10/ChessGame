package bcit.ca.comp2526.ChessGame;

import java.io.Serializable;

/**
 * Class for King pieces, inherits all methods from ChessPiece.
 * 
 * @author Kerry Regan
 * @version 1.0
 */
public class King extends ChessPiece implements Serializable {

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
    public King(final String name, final boolean color, final String path) {
        super(name, color, path);
    }

    /**
     * Makes sure the move is valid, can only move one space in all directions.
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
        
        return ((diffX == 1 && diffY == 1) || (diffX == 0 && diffY == 1) 
                || (diffX == 1 && diffY == 0));

    }

    /**
     * Tests if the path is clear in all directions.  Since the King
     * can only move one space, this will always return true.
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
            final int endY,
            final ChessSquare[][] chessSquares) {
        return true;
    }
}
