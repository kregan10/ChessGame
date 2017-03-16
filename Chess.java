package bcit.ca.comp2526.ChessGame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * Main driver class for the Chess game. Sets up ChessBoard, and listeners for
 * open and save buttons
 * 
 * @author Kerry Regan
 * @version 1.0
 */
public class Chess extends JFrame implements Serializable {

    /**
     * To remove warnings.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Setting up save Menu Item.
     */
    private final JMenuItem save = new JMenuItem("Save");

    /**
     * Setting up open Menu Item.
     */
    private final JMenuItem open = new JMenuItem("Open");

    /**
     * Setting up open Menu to contain open and save buttons.
     */
    private final JMenuBar menuBar = new JMenuBar();

    /**
     * Initializing chess board.
     */
    private ChessBoard chessBoard;

    /**
     * Window Width.
     */
    private final int windWidth = 400;

    /**
     * Window height.
     */
    private final int windHeight = 700;
    
    /**
     * Button width.
     */
    private final int butonWidth = 50;
    
    /**
     * Button height.
     */
    private final int buttonHeight = 20;
    
    /**
     * Menu box width.
     */
    private final int menuBoxWidth = 100;
    
    /**
     * Button height.
     */
    private final int menuBoxHeight = 20;
    

    /**
     * Constructor for chess game. Sets up main JFrame for the game.
     */
    public Chess() {

        this.chessBoard = new ChessBoard();
        menuBar.add(Box.createRigidArea(new Dimension(menuBoxWidth,
                menuBoxHeight)));
        menuBar.setVisible(true);
        menuBar.setBorderPainted(true);

        save.setPreferredSize(new Dimension(butonWidth, buttonHeight));
        open.setPreferredSize(new Dimension(butonWidth, buttonHeight));
        open.setName("open");
        save.setName("save");
        save.addActionListener(new StateListner());
        open.addActionListener(new StateListner());
        menuBar.add(save);
        menuBar.add(open);

        this.add(menuBar);
        this.add(chessBoard);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(windWidth, windHeight);
        this.getContentPane().add(chessBoard);
        this.setMinimumSize(this.getSize());
        this.pack();
        this.setVisible(true);
    }

    /**
     * Gets open menuItem.
     * 
     * @return this.open
     */
    JMenuItem getOpen() {
        return this.open;
    }
    
    /**
     * Gets open menuItem.
     * 
     * @return this.save
     */
    JMenuItem getSave() {
        return this.save;
    }
    
    /**
     * Gets current chess board.
     * 
     * @return this.chessboard Current chessboard
     */
    JPanel getBoard() {
        return this.chessBoard;
    }

    /**
     * Main entry into program, sets up ChessBoard object and adds it to the
     * main frame.
     * 
     * @param args
     *            Command-line argument
     */
    public static void main(String[] args) {

        Chess game = new Chess();
        BoxLayout boxLayout = new BoxLayout(game.getContentPane(),
                BoxLayout.Y_AXIS);
        game.setLayout(boxLayout);

    }

    /**
     * Creates a save file called "test.gam" and saves the file to the state of
     * this chess object to the filestream.
     * 
     * @throws IOException
     *             Throws IOException if save failed
     */
    void saveGame() throws IOException {
        try {
            final FileOutputStream fileOut = new FileOutputStream("test.gam");
            final ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.getBoard());
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Opens the save file called "test.gam" and throws exception if the class
     * was not found.
     * 
     * @throws ClassNotFoundException
     *             Throws ClassNotFoundException if the file was unable to be
     *             retrieved.
     */
    void openGame() throws ClassNotFoundException {
        try {

            final FileInputStream fileIn = new FileInputStream("test.gam");
            final ObjectInputStream in = new ObjectInputStream(fileIn);

            this.remove(chessBoard);
            this.chessBoard = (ChessBoard) in.readObject();
            this.add(chessBoard);

            this.revalidate();
            this.repaint();

            in.close();
            fileIn.close();

        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * 
     * Listener for open and save menu items. Calls openGame or saveGame of the
     * outer class.
     * 
     * @author Kerry Regan
     */
    public class StateListner implements ActionListener, Serializable {

        /**
         * To remove warning.
         */
        private static final long serialVersionUID = 1L;

        /**
         * Performs action on button press.
         * 
         *@param e
         *       a button that has been clicked on.
         */
        public void actionPerformed(final ActionEvent e) {

            if (e.getSource().equals(open)) {
                try {
                    openGame();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            } else {
                try {
                    saveGame();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        }
    }
}
