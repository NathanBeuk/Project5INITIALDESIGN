import java.util.ArrayList;
/**
 * Description a sub class of the abstract piece class
 * @author Adoniram Courser and Nathan Beukema
 * @version 1.0
 * @since 11-29-23
 */
public class Pawn extends Piece{

    /**
     *
     * @param black
     */
    public Pawn(boolean black){
        super(black);
    }

    /**
     *
     * @return an arrayList of coordinates for possible moves
     */
    @Override
    public ArrayList<int[]> possibleMoves(){
        //method for pawn take because only piece with different take vs regular move
        return new ArrayList<int[]>();
    }

    /**
     *
     * @return XYValue for en passant, will likely be replaced by the more general possible moves method
     */
    public int[] specialMove(){
        int[] XYValue = {0,0};
        return XYValue;
    }


    /**
     *
     * @return the positions for a pawn taking pieces since they are different from normal pawn movement unlike most pieces
     */
    public int pawnTake(){
        return 0;
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

