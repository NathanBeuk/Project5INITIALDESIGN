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
    private Stack<GameRules> actualGame = new Stack<>();
    private Stack<GameRules> removedMoves = new Stack<>();

    /**
     * constructor
     */
    public GameSequence(GameRules startingBoard){
        actualGame.push(startingBoard);
    }

    /**
     * returns a boards at a certain index (number of moves since beginning of game)
     * @return
     */

    //takes most recent move from play and puts it onto a rewound moves stack
    public GameRules gameRewind(){
        removedMoves.push(actualGame.pop());
        return new GameRules();
    }


    // takes most recent move from removedmoves and reinserts it onto the actual game stack
    public GameRules gameForward(){
        actualGame.push(removedMoves.pop());
        return new GameRules();
    }
}
