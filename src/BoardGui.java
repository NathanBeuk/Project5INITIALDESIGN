import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardGui extends JFrame {
    private String[][] currentBoard;
    private JButton[][] buttons;

    public BoardGui(String[][] board){

        currentBoard = board;
        buttons = new JButton[8][8];

        //following lines of code are possible because it extends the JFrame class, meaning we don't have to call new JFrame (since this is a JFrame)
        setTitle("Chess");//just sets the title of the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//apparently this makes sure that it closes when we click close lol
        setLayout(new GridLayout(8, 8));//makes sure its set up as a grid

        //following generates new JButtons and adds them to two-D array of them while referencing new board
        Color tan = new Color(210, 180, 140);
        Color black = Color.black;
        Color darkRed = new Color(139, 0 ,0);
        for (int i = 0; i < 8; i = i + 1){
            for (int j = 0; j < 8; j = j +1){

                buttons[i][j] = new JButton();
                buttons[i][j].setText(currentBoard[i][j]);
                buttons[i][j].addActionListener(new ButtonClickListener(i,j,this));

                if (i + j == 0 || (i + j) % 2 == 0){
                    buttons[i][j].setBackground(tan);//should be tan-ish
                }
                else {
                    buttons[i][j].setBackground(darkRed);
                }
                add(buttons[i][j]);//adds button to content pane of frame so its presented

            }
        }
        setSize(400,400);//sets size of frame I think
        setLocationRelativeTo(null);//centers it I think lol im just going off description TODO make sure this works the way I think it does
        setVisible(true);
    }

    public void displayValue(int row, int column){
        JOptionPane.showMessageDialog(this, currentBoard[row][column]);
    }

    public void updateBoard(String[][] board){
    }
}