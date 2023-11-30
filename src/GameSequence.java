import java.util.ArrayList;
/**
 * Description
 * @author Adoniram Courser and Nathan Beukema
 * @version 1.0
 * @since 11-29-23
 */
public class GameSequence {

    /**
     * an ArrayList to hold all the boards
     */
    private ArrayList<GameBoard> list = new ArrayList<>();

    /**
     * constructor
     */
    public GameSequence(){

    }

    /**
     * returns a boards at a certain index (number of moves since beginning of game)
     * @param index
     * @return
     */
    public GameBoard gameAt(int index){

        return new GameBoard();
    }
}
