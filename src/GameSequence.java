import java.util.Stack;

/**
 * This is the class that contains the function for rewinding and fast-forwarding through the played game thus far.
 *
 * @author Adoniram Courser and Nathan Beukema
 * @version 1.0
 * @since 11-29-23
 */
public class GameSequence {

    /**
     * Stacks for holding past boards, and future boards
     * presentBoard is the current state of the chess board
     */
    private Stack<String[][]> pastBoards = new Stack<>();
    private String[][] presentBoard;
    private Stack<String[][]> futureBoards = new Stack<>();

    /**
     * constructor
     */
    public GameSequence(String[][] startingBoard) {
        presentBoard = deepCopy(startingBoard);
    }


    /**
     * @param newBoard a representation of the game board after a move is made
     * @return moves the presentBoard onto the pastBoards stack, to leave room for the next presentBoard.
     * Clears the stack of futureBoards after a move is made, since that future is no longer playable
     */
    public void move(String[][] newBoard) {
        pastBoards.push(presentBoard);
        presentBoard = deepCopy(newBoard);
        if (futureBoards.size() > 0) {
            futureBoards.clear();
        }
    }

    /**
     * @param board a representation of the game board
     * @return returns a boards at a certain index (number of moves since beginning of game).
     * Takes most recent move from play and puts it onto a rewound moves stack.
     */
    public String[][] deepCopy(String[][] board) {
        String[][] copy = new String[board.length][board[0].length];

        for (int i = 0; i < board.length; i = i + 1) {
            for (int j = 0; j < board[0].length; j++) {
                String string = board[i][j];
                copy[i][j] = string + "";
            }
        }
        return copy;
    }

    /**
     * @return returns a boards at a certain index (number of moves since beginning of game).
     * Takes most recent move from play and puts it onto a rewound moves stack.
     */
    public String[][] gameRewind() {
        if (pastBoards.size() != 0) {
            futureBoards.push(presentBoard);
            presentBoard = deepCopy(pastBoards.pop());
        }
        return deepCopy(presentBoard);
    }

    /**
     * @return returns a boards at a certain index (number of moves since beginning of game).
     * takes most recent move from removed moves and reinserts it onto the actual game stack.
     */
    public String[][] gameForward() {
        if (futureBoards.size() != 0) {
            pastBoards.push(presentBoard);
            presentBoard = deepCopy(futureBoards.pop());
        }
        return deepCopy(presentBoard);
    }
}
