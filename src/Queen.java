import java.util.ArrayList;

public class Queen extends Piece{

    public Queen(boolean black){
        super(black);
    }


    @Override
    public ArrayList<int[]> possibleMoves(){

        return new ArrayList<int[]>();
    }

    @Override
    public Piece newCopy() {
        return null;
    }
}
