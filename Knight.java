package bcit.ca.comp2526.ChessGame;

import java.io.Serializable;

/**
 * Class for Knight pieces, inherits all methods from ChessPiece.
 * 
 * @author Kerry Regan
 * @version 1.0
 */
public class Knight extends ChessPiece implements Serializable {

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
    public Knight(String name, boolean color, String path) {
        super(name, color, path);
    }

    /**
     * Test if the move is valid, and can move only in "L".
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
    boolean isMoveValid(int startX, int startY, int endX, int endY) {
        int diffX = Math.abs(endX - startX);
        int diffY = Math.abs(endY - startY);
        return (diffX == 2 && diffY == 1) || (diffX == 1 && diffY == 2);
    }

    /**
     * The knight is able to jump over pieces, so it only 
     * needs to check if destination is either not occupied, 
     * or is occupied by enemy.
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
    boolean isPathClear(int startX, int startY, int endX, int endY,
            ChessSquare[][] chessSquares) {
        return !chessSquares[endY][endX].isOccupied()
                || (chessSquares[endY][endY].occupiedBy().getColor()
                        != this.getColor());
    }

}
