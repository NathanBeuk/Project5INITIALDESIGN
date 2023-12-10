import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class BoardGui extends JFrame {
    private String[][] currentBoard;

    private JButton[][] buttons;

    private ArrayList<int[]> lastSelectedPiecesPossibleMoves;
    private int[] lastSelectedPosition;

    private GameRules gameRules;
    private GameSequence gameSequence;

    private char team;


    public BoardGui(String[][] board){

        currentBoard = board;
        buttons = new JButton[board.length][board[0].length];
        lastSelectedPiecesPossibleMoves = new ArrayList<>();
        lastSelectedPosition = new int[]{0, 0};//TODO invalid selection
        gameRules = new GameRules();
        gameSequence = new GameSequence(board);
        team = 'W';



        //following lines of code are possible because it extends the JFrame class, meaning we don't have to call new JFrame (since this is a JFrame)
        setTitle("Chess");//just sets the title of the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//apparently this makes sure that it closes when we click close lol
        setLayout(new GridLayout(8, 8));//makes sure its set up as a grid

        //following generates new JButtons and adds them to two-D array of them while referencing new board

        Dimension buttonSize = new Dimension(50, 50);

        for (int i = 0; i < 8; i = i + 1){
            for (int j = 0; j < 9; j = j +1){//9 columns for commands lines

                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(new ButtonClickListener(i,j,this));
                buttons[i][j].setPreferredSize(buttonSize);

                add(buttons[i][j]);//adds button to content pane of frame so its presented
            }
        }
        setBoardText();
        normalizeColors();

        setSize(600,600);//sets size of frame I think
        setLocationRelativeTo(null);//centers it I think lol im just going off description TODO make sure this works the way I think it does
        setVisible(true);
    }

    public void setBoardText(){
        for (int i = 0; i < 8; i = i + 1){
            for (int j = 0; j < 9; j = j +1){//9 columns for commands lines

                buttons[i][j].setText(currentBoard[i][j]);

            }
        }
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

    public boolean isInArrayList(int row, int column, ArrayList<int[]> possibleMoves, int[] lastposition){//checks if the last selected position was a valid tile, and if current selected tile in in possible moves for last selected tile
        if (gameRules.validPosition(lastposition[0], lastposition[1], currentBoard)){

            for (int i = 0; i < possibleMoves.size(); i = i +1){
                if (possibleMoves.get(i)[0] == row && possibleMoves.get(i)[1] == column){
                    possibleMoves.clear();
                    return true;
                }
            }
        }
        return false;
    }

    public void colorPossibleMoves(ArrayList<int[]> possibleMoves){//TODO not finished I think
        for (int i = 0; i < possibleMoves.size(); i = i + 1){
            buttons[possibleMoves.get(i)[0]][possibleMoves.get(i)[1]].setBackground(Color.BLUE);
        }
    }
    public void displayValue(int row, int column){
        boolean pieceShouldMove = isInArrayList(row, column, lastSelectedPiecesPossibleMoves, lastSelectedPosition);

        //TODO checking if button was even a valid piece and not a command button
        //TODO valid index
        normalizeColors();

        if (pieceShouldMove){
            //TODO move piece somehow, well calculations will already have been done so maybe I just move the string in both array, and button text, and
            currentBoard[row][column] = currentBoard[lastSelectedPosition[0]][lastSelectedPosition[1]];//moves piece to position in game
            currentBoard[lastSelectedPosition[0]][lastSelectedPosition[1]] = "";

            if (team == 'W'){
                team = 'B';
            }else {
                team = 'W';
            }

            currentBoard = gameRules.flipBoard(currentBoard);//flips board after piece was moved
            setBoardText();//changes the text on the frame


        }
        else {
            lastSelectedPosition[0] = row;
            lastSelectedPosition[1] = column;


            buttons[row][column].setBackground(Color.cyan);
            String lastPiece = currentBoard[row][column];
            if (lastPiece.length() > 0){
                if (lastPiece.charAt(2) == team){
                    ArrayList<int[]> possibleMoves = gameRules.getPossibleMoves(row, column, currentBoard);
                    lastSelectedPiecesPossibleMoves = possibleMoves;

                    colorPossibleMoves(possibleMoves);
                    System.out.println(currentBoard[row][column]);
                }
            }
        }
    }
}