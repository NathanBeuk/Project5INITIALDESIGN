import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;


/**
 * Description
 * @author Adoniram Courser and Nathan Beukema
 * @version 1.0
 * @since 11-29-23
 */
public class GameBoard {






    /**
     * a Two dimensional array for a single board
     */
    private GameBoard[][]board = new GameBoard[8][8];

    /**
     * constructor
     */
    public GameBoard(){

    }

    /**
     * a method to move a piece in the two-D arrayList
     * It may make more sense for it to copy itself and return an edited version of its self
     */
    public void MovePiece(){

    }

    /**
     * a method to remove a piece in the two-D arrayList
     * It may make more sense for it to copy itself and return an edited version of its self
     */
    public void RemovePiece(){

    }


    /**
     * gets a position on the board
     *
     * @param row
     * @param col
     * @return
     */
    public GameBoard getSpace(int row, int col){

        return board[row][col];
    }
}
