import java.util.ArrayList;

public class CheckersPiece extends Piece{
    /**
     * @param black
     */
    public CheckersPiece(boolean black) {
        super(black);
    }

    @Override
    public ArrayList<int[]> possibleMoves() {
        return null;
    }

    @Override
    public Piece newCopy() {
        return null;
    }
}
