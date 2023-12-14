import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Description: implements ActionListener so that it can be used in JButtons
 * @author Adoniram Courser and Nathan Beukema
 * @version 1.0
 * @since 12-13-23
 *
 */
public class ButtonActionListener implements ActionListener {

    private int row;
    private int column;
    private Board board;

    /**
     * Description: ButtonActionListener constuctor
     * @param row: row of the button
     * @param column: column of the button
     * @param board: frame that the button belongs to
     */
    public ButtonActionListener(int row, int column, Board board) {
        this.row = row;
        this.column = column;
        this.board = board;
    }

    /**
     * Description: the overridden actionPerformed method that calls the button pressed method so that the board can process the user input
     * @param event the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        board.buttonPressed(row, column);
    }
}
