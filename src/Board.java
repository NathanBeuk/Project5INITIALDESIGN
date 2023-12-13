import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JFrame {
    private String[][] currentBoard;
    private JButton[][] buttons;
    private ArrayList<int[]> lastSelectedPiecesPossibleMoves;
    private int[] lastSelectedPosition;
    private GameRules gameRules;
    private GameSequence gameSequence;
    private char team;


    public Board(String[][] board) {

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

        for (int i = 0; i < 8; i = i + 1) {
            for (int j = 0; j < 9; j = j + 1) {//9 columns for commands lines

                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(new ButtonClickListener(i, j, this));
                buttons[i][j].setPreferredSize(buttonSize);

                add(buttons[i][j]);//adds button to content pane of frame so its presented
            }
        }
        setBoardText();
        normalizeColors();

        setSize(600, 600);//sets size of frame I think
        setLocationRelativeTo(null);//centers it I think lol im just going off description TODO make sure this works the way I think it does
        setVisible(true);
    }

    /**
     * Sets the board to correctly display the input of the starterBoard
     */
    public void setBoardText() {
        for (int i = 0; i < 8; i = i + 1) {
            for (int j = 0; j < 9; j = j + 1) {//9 columns for commands lines

                buttons[i][j].setText(currentBoard[i][j]);

            }
        }
    }

    /**
     * Sets the board to correct chessboard colors. Command line is left white
     */
    public void normalizeColors() {
        Color tan = new Color(210, 180, 140);//should be tan-ish
        Color darkRed = new Color(200, 0, 0);//should be dark red ish
        Color white = Color.WHITE;
        Color black = Color.black;
        for (int i = 0; i < 8; i = i + 1) {
            for (int j = 0; j < 9; j = j + 1) {//9 columns for commands lines
                if (j == 0) { //sets command line color
                    buttons[i][j].setBackground(white);
                } else if ((i + j - 1) % 2 == 0) {//if the index is a magnitude of 0 or an even number the thing is tan
                    buttons[i][j].setBackground(tan);
                } else {
                    buttons[i][j].setBackground(darkRed);
                }
                buttons[i][j].setBorder(new LineBorder(black, 2));// apparently 2 pixels thick
            }
        }
    }

    public boolean shouldPieceMove(int row, int column, ArrayList<int[]> possibleMoves, int[] lastposition) {//checks if the last selected position was a valid tile, and if current selected tile in possible moves for last selected tile
        if (gameRules.validPosition(lastposition[0], lastposition[1], currentBoard)) {

            for (int i = 0; i < possibleMoves.size(); i = i + 1) {
                if (possibleMoves.get(i)[0] == row && possibleMoves.get(i)[1] == column) {
                    possibleMoves.clear();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param possibleMoves ArrayList of possible moves for a selected piece
     * @return colors each possible move for selection by the user
     */
    public void colorPossibleMoves(ArrayList<int[]> possibleMoves) {//TODO not finished I think

        Color color = new Color(255, 153, 0);

        for (int i = 0; i < possibleMoves.size(); i = i + 1) {
            buttons[possibleMoves.get(i)[0]][possibleMoves.get(i)[1]].setBackground(color);
        }
    }

    /**
     * Swaps team after each move that is made
     */
    public void teamSwap() {
        if (team == 'W') {
            team = 'B';
        } else {
            team = 'W';
        }
    }


    /**
     * @param row    row index of the button that is pressed
     * @param column column index of the button that is pressed
     * @return chooses the correct action to take based off of the state of the button selected
     */
    public void buttonPressed(int row, int column) {
        if (column == 0) {//fires if pressed a command button so no rules need be referenced
            if (row == 0) {
                //forward
                teamSwap();
                currentBoard = gameSequence.gameForward();
                setBoardText();//changes the text on the buttons in frame so can see change

            } else if (row == 1) {
                //backward
                teamSwap();
                currentBoard = gameSequence.gameRewind();
                setBoardText();//changes the text on the buttons in frame so can see change

            } else if (row == 7) {
                //ends game
                System.out.println("Thank you for playing");
                System.exit(0);
            }
        } else {//fires if selected a board tile
            boolean pieceShouldMove = shouldPieceMove(row, column, lastSelectedPiecesPossibleMoves, lastSelectedPosition);

            normalizeColors();
            if (pieceShouldMove) {
                String movingPiece = currentBoard[lastSelectedPosition[0]][lastSelectedPosition[1]];

                int timesMoved = Integer.parseInt(movingPiece.substring(3)) + 1;
                movingPiece = movingPiece.substring(0, 3) + timesMoved;

                if (gameRules.pawnToQueen(row, movingPiece)) {
                    //System.out.println("hello this works");
                    movingPiece = "Q" + movingPiece.substring(1);
                }
                String pieceCaptured = currentBoard[row][column];

                currentBoard[row][column] = movingPiece;//moves piece to position in game
                currentBoard[lastSelectedPosition[0]][lastSelectedPosition[1]] = "";


                if (!pieceCaptured.equals("")) {
                    if (pieceCaptured.charAt(0) == 'K') {
                        System.out.println("Team " + team + " has won the game!!!!");
                        System.out.println("Thank you for playing");
                        System.exit(0);
                    }
                }
                teamSwap();

                currentBoard = gameRules.flipBoard(currentBoard);//flips board after piece was moved

                setBoardText();//changes the text on the buttons in frame so can see change

                gameSequence.move(currentBoard);
            } else {
                lastSelectedPosition[0] = row;
                lastSelectedPosition[1] = column;

                buttons[row][column].setBackground(Color.cyan);
                String lastPiece = currentBoard[row][column];
                if (lastPiece.length() > 0) {//if piece selected isn't an empty string
                    if (lastPiece.charAt(2) == team) {
                        ArrayList<int[]> possibleMoves = gameRules.getPossibleMoves(row, column, currentBoard);
                        lastSelectedPiecesPossibleMoves = possibleMoves;

                        colorPossibleMoves(possibleMoves);
                    }
                }
            }
        }
    }
}