import java.util.ArrayList;

public class Rook extends Piece{

    public Rook(boolean black){
        super(black);
    }



    @Override
    public ArrayList<int[]>possibleMoves(){

        return new ArrayList<int[]>();
    }
    @Override
    public Piece newCopy() {
        return null;
    }
}
