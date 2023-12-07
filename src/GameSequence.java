import java.util.ArrayList;
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
    private Stack<GameBoard> actualGame = new Stack<>();
    private Stack<GameBoard> removedMoves = new Stack<>();

    /**
     * constructor
     */
    public GameSequence(GameBoard startingBoard){
        actualGame.push(startingBoard);
    }

    /**
     * returns a boards at a certain index (number of moves since beginning of game)
     * @return
     */

    //takes most recent move from play and puts it onto a rewound moves stack
    public GameBoard gameRewind(){
        removedMoves.push(actualGame.pop());
        return new GameBoard();
    }


    // takes most recent move from removedmoves and reinserts it onto the actual game stack
    public GameBoard gameForward(){
        actualGame.push(removedMoves.pop());
        return new GameBoard();
    }
}
