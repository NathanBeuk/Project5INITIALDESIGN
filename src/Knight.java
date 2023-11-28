import java.util.ArrayList;

public class Knight extends Piece{

    public Knight(boolean black){
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
