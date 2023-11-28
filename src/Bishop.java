import java.util.ArrayList;

public class Bishop extends Piece{

    public Bishop(boolean black){
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
