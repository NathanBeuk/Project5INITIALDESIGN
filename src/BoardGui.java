import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardGui extends JFrame {
    private String[][] currentBoard;

    private JButton[][] buttons;


    public BoardGui(String[][] board){

        currentBoard = board;
        buttons = new JButton[board.length][board[0].length];

        //following lines of code are possible because it extends the JFrame class, meaning we don't have to call new JFrame (since this is a JFrame)
        setTitle("Chess");//just sets the title of the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//apparently this makes sure that it closes when we click close lol
        setLayout(new GridLayout(8, 8));//makes sure its set up as a grid

        //following generates new JButtons and adds them to two-D array of them while referencing new board

        Dimension buttonSize = new Dimension(50, 50);

        for (int i = 0; i < 8; i = i + 1){
            for (int j = 0; j < 9; j = j +1){//9 columns for commands lines

                buttons[i][j] = new JButton();
                buttons[i][j].setText(currentBoard[i][j]);
                buttons[i][j].addActionListener(new ButtonClickListener(i,j,this));
                buttons[i][j].setPreferredSize(buttonSize);

                add(buttons[i][j]);//adds button to content pane of frame so its presented
            }
        }
        normalizeColors();

        setSize(600,600);//sets size of frame I think
        setLocationRelativeTo(null);//centers it I think lol im just going off description TODO make sure this works the way I think it does
        setVisible(true);
    }


    public void normalizeColors(){
        Color tan = new Color(210, 180, 140);//should be tan-ish
        Color darkRed = new Color(139, 0 ,0);//should be dark red ish
        Color white = Color.WHITE;
        Color black = Color.black;
        for (int i = 0; i < 8; i = i + 1){
            for (int j = 0; j < 9; j = j +1){//9 columns for commands lines
                if (j == 0){
                    buttons[i][j].setBackground(white);
                }
                else if ((i + j-1) % 2 == 0){//if the index is a magnitude of 0 or an even number the thing is tan
                    buttons[i][j].setBackground(tan);
                }
                else {
                    buttons[i][j].setBackground(darkRed);
                }
                buttons[i][j].setBorder(new LineBorder(black, 2));// apparently 2 pixels thick
            }
        }
    }
    public void displayValue(int row, int column){
        normalizeColors();
        buttons[row][column].setBackground(Color.cyan);
        JOptionPane.showMessageDialog(this, currentBoard[row][column]);
    }

    public void updateBoard(String[][] board){
    }
}