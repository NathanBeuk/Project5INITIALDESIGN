import java.util.ArrayList;

/**
 * Description
 * @author Adoniram Courser and Nathan Beukema
 * @version 1.0
 * @since 11-29-23
 */
public abstract class Piece {
    /**
     * @Class Variable black
     */
    private boolean black;

    /**
     *
     * @param black
     */
    public Piece(boolean black){
        this.black = black;
    }

    /**
     *
     * @param x
     * @param y
     * @return a boolean for whether a move is valid or not
     */
    public boolean ValidMove(int x, int y){
        ArrayList<int[]> possibleMoves = possibleMoves();
        boolean validMove = false;
        for (int i = 0; i < possibleMoves.size();i = i + 1){
            if (x == possibleMoves.get(i)[0] && y == possibleMoves.get(i)[1]){
                validMove = true;
            }
        }
        return validMove;
    }
    /**
     *
     * @return an arrayList of coordinates for possible moves
     */
    public abstract ArrayList<int[]>possibleMoves();


    /**
     * returns a copy of a piece (though since no piece of a specific sub-class has any unique class variables a copy method is likely unessesary
     * however the idea for this is to be used in tandum with the game sequence class to save entire boards
     * @return Piece
     */
    public abstract Piece newCopy();


}
