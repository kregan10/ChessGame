package bcit.ca.comp2526.ChessGame;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.JButton;

/**
 * Chess square class which is a JButton.  Each
 * square may or may not contain a piece.
 * 
 * @author Kerry Regan
 * @version 1.0
 */
public class ChessSquare extends JButton implements Serializable {

    /**
     * The size of a square, this dictates the overall
     * size of the board.
     */
    public static final int SQUARE_SIDE = 80;
    
    /**
     * To remove warning.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The current piece that is occupying the square.
     */
    private ChessPiece occupiedBy;
    
    /**
     * A placeholder for the default color of the ChessSquare.
     */
    private Color color;

    
    /**
     * If a piece is currently occupying the square, then this would be equal to
     * true.
     */
    private boolean isOccupied;
    
    
    /**
     * Constructor for chess square. Creates a equal sided
     * rectangle.
     * @param x
     *          X location of the Chess Square
     * @param y
     *          Y location of the Chess Square
     */
    ChessSquare(int x, int y) {
        new Rectangle(x, y, SQUARE_SIDE, SQUARE_SIDE);
        this.vacate();
    }

    /**
     * Takes in a ChessPiece object, and sets the piece to the
     * current square.  The method then repaints the square with
     * the newly added icon.
     * 
     * @param piece
     *           Piece currently occcupying this square.
     */
    void occupy(ChessPiece piece) {
        this.occupiedBy = piece;
        this.isOccupied = true;
        this.setIcon(piece.getIcon());
        this.revalidate();
        this.repaint();
    }

    /**
     * Removes the current ChessPiece object and icon from the 
     * ChessSquare and repaints it. 
     */
    public void vacate() {
        this.occupiedBy = null;
        this.isOccupied = false;        
        this.setIcon(null);
        this.validate();
        this.repaint();
    }
    
    /**
     * Returns true if a piece is currently occupying the Chess Square.
     * @return
     *      isOccupied
     */
    boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Sets the color to Chess Square.  This is used for letting the user
     * know the selected square.
     * 
     * @param color
     *              the new color to set the square to.
     */
    void setColor(Color color) {
        this.color = color;
    }
    
    /**
     * Once a user has moved a piece, this method is called to set the color
     * back to what it was originally.
     */
    void setColorToDefault() {
        this.setBackground(color);    
    }
    
    /**
     * Returns the X location of the ChessSquare, divided by the SQUARE_SIDE
     *  to account for the square size.
     *  
     *  @return X location of the square in the chessSquare array.
     */
    int getXLoc() {
        return getX() / SQUARE_SIDE;
    }
    
    /**
     * Returns the X location of the ChessSquare, divided by the SQUARE_SIDE
     * to account for the square size.
     * 
     * @return Y location of the square in the chessSquare array.
     */
    int getYLoc() {
        return getY() / SQUARE_SIDE;
    }
    
    /**
     * Returns the curren ChessPiece occupying the square.
     * @return
     *      ChessPiece
     */
    ChessPiece occupiedBy() {
        return occupiedBy;
    }

}
