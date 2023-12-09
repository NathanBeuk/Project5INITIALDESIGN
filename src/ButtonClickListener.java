import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ButtonClickListener implements ActionListener {

    private int row;
    private int column;
    private BoardGui boardGui;

    public ButtonClickListener(int row, int column, BoardGui boardGui){
        this.row = row;
        this.column = column;
        this.boardGui = boardGui;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        boardGui.displayValue(row, column);
    }
}
