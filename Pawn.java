package bcit.ca.comp2526.ChessGame;

import java.io.Serializable;

/**
 * Class for Pawn pieces, inherits all methods from ChessPiece.
 * 
 * @author Kerry Regan
 * @version 1.0
 */
public class Pawn extends ChessPiece implements Serializable {

    /**
     * To remove warnings.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Pawn, calls ChessPiece constructor with specified file
     * path and name.
     * 
     * @param name
     *            name of piece
     * @param color
     *            color of piece
     * @param path
     *            filepath of the ImageIcon
     */
    public Pawn(String name, boolean color, String path) {
        super(name, color, path);
    }

    /**
     * Determines if the move is valid.  The pawn can move two spaces if it 
     * is this pawns first move, otherwise it can only move up or down one 
     * space depending on if it is black or white.
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
    boolean isMoveValid(final int startX, final int startY, final int endX, 
            final int endY) {
        final int diffY = endY - startY;
        final int twoSquares = 2;
        final boolean isFirstMove = this.getNumOfMoves() == 0;

        if (getColor() == getWhite()) {
            if (isFirstMove) {
                return (startX == endX && diffY == -twoSquares)
                        || (startX == endX && diffY == -1);
            } else {
                return (startX == endX && diffY == -1);
            }
        } else {
            if (isFirstMove) {
                return (startX == endX && diffY == twoSquares)
                        || (startX == endX && diffY == 1);
            } else {
                return (startX == endX && diffY == 1);
            }
        }
    }

    /**
     * Checks if the attack move is valid.  Pawns can only move 
     * diagonally when attacking. 
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
    boolean isAttackMoveValid(final int startX, final int startY, 
            final int endX, final int endY) {
        int diffX = endX - startX;
        int diffY = endY - startY;

        if (getColor() == getWhite()) {
            return (diffX == 1 || diffX == -1) 
                    && diffY == -1;
        } else {
            return (diffX == 1 || diffX == -1)
                    && diffY == 1;
        }
    }

    /**
     * Checks if the path is clear.  Since pawns only move one space
     * at a time, this will always be true. 
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
     *            Squares to be checked
     * @return True or false if the path is clear
     */
    boolean isPathClear(final int startX, final int startY, final int endX, 
            final int endY, final ChessSquare[][] chessSquares) {
        return true;
    }
}
