import java.util.Stack;

/**
 * Description
 * @author Adoniram Courser and Nathan Beukema
 * @version 1.0
 * @since 11-29-23
 */
public class GameSequence {

    /**
     * A stack to hold all the boards
     */
    private Stack<String[][]> pastBoards = new Stack<>();
    private String[][] presentBoard;
    private Stack<String[][]> futureBoards = new Stack<>();

    /**
     * constructor
     */
    public GameSequence(String[][] startingBoard){
        presentBoard = deepCopy(startingBoard);
    }

    public String[][] move(String[][] newBoard){

        pastBoards.push(presentBoard);
        presentBoard = deepCopy(newBoard);


        return presentBoard;
    }
    public String[][] deepCopy(String[][] board){
        String[][] copy = new String[board.length][board[0].length];

        for (int i = 0; i < board.length; i = i + 1){
            for (int j = 0; j < board[0].length; j++) {
                String string = board[i][j];
                copy[i][j] = string + "";

            }
        }
        return copy;
    }
    /**
     * returns a boards at a certain index (number of moves since beginning of game)
     * @return
     */

    //takes most recent move from play and puts it onto a rewound moves stack
    public String[][] gameRewind(){
        if (pastBoards.size() != 0){
            futureBoards.push(presentBoard);
            presentBoard = pastBoards.pop();
        }
        return presentBoard;
    }


    // takes most recent move from removed moves and reinserts it onto the actual game stack
    public String[][] gameForward(){
        if (futureBoards.size() != 0) {
            pastBoards.push(presentBoard);
            presentBoard = futureBoards.pop();
        }

        return presentBoard;
    }
}
