package bcit.ca.comp2526.ChessGame;

import java.io.Serializable;

/**
 * Class for Rook pieces, inherits all methods from ChessPiece.
 * 
 * @author Kerry Regan
 * @version 1.0
 */
public class Rook extends ChessPiece implements Serializable {

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
    public Rook(final String name, final boolean color, final String path) {
        super(name, color, path);
    }

    /**
     * Tests the validity of movement. Can only move up or down.
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
        return startX == endX || startY == endY;
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
     *            through.
     * @return True or false if path is occupied or not.
     */
    boolean isPathClear(final int startX, final int startY, final int endX, 
            final int endY, final ChessSquare[][] chessSquares) {

        final boolean up = endY < startY;
        final boolean down = endY > startY;
        final boolean left = endX < startX;
        final boolean right = endX > startX;
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
        }

        return isPathClear;
    }
}