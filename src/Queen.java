import java.util.ArrayList;

/**
 * Description a sub class of the abstract piece class
 * @author Adoniram Courser and Nathan Beukema
 * @version 1.0
 * @since 11-29-23
 */
public class Queen extends Piece{

    /**
     *
     * @param black
     */
    public Queen(boolean black){
        super(black);
    }

    /**
     *
     * @return an arrayList of coordinates for possible moves
     */
    @Override
    public ArrayList<int[]> possibleMoves(){

        return new ArrayList<int[]>();
    }
    /**
     * returns a copy of a piece (though since no piece of a specific sub-class has any unique class variables a copy method is likely unessesary
     * however the idea for this is to be used in tandum with the game sequence class to save entire boards
     * @return Piece
     */
    @Override
    public Piece newCopy() {
        return null;
    }
}
