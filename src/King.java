import java.util.ArrayList;

public class King extends Piece{

    public King(boolean black){
        super(black);
    }



    @Override
    public ArrayList<int[]> possibleMoves(){

        return new ArrayList<int[]>();
    }

    // for castling either king or queenside. Will need to incorporate Rook class
    public int specialMove(){
        return 0;
    }

    @Override
    public Piece newCopy() {
        return null;
    }
}
