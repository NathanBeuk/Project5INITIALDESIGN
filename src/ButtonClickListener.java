import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClickListener implements ActionListener {

    private int row;
    private int column;
    private Board boardGui;

    public ButtonClickListener(int row, int column, Board boardGui){
        this.row = row;
        this.column = column;
        this.boardGui = boardGui;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        boardGui.buttonPressed(row, column);
    }
}
