import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Description: implements
 * @author Adoniram Courser and Nathan Beukema
 * @version 1.0
 * @since 11-29-23
 *
 */
public class ButtonActionListener implements ActionListener {

    private int row;
    private int column;
    private Board boardGui;

    public ButtonActionListener(int row, int column, Board boardGui) {
        this.row = row;
        this.column = column;
        this.boardGui = boardGui;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        boardGui.buttonPressed(row, column);
    }
}
