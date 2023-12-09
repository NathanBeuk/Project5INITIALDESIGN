package OldChessPieces;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Description a sub class of the abstract piece class
 * @author Adoniram Courser and Nathan Beukema
 * @version 1.0
 * @since 11-29-23
 */
public class King extends Piece {

    /**
     *
     * @param black
     */
    public King(boolean black){
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

    // for castling either king or queenside. Will need to incorporate Rook class

    /**
     * will likely be replaced by the more general possible moves method
     * @return XYValue for castling king however it will need to also run a check to see the condition of the rook it intends to castle with but that comes later
     */
    public int[] specialMove(){
        int[] XYValue = {0,0};
        return XYValue;
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
