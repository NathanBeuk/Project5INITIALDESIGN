import java.util.ArrayList;

public class Pawn extends Piece{

    public Pawn(boolean black){
        super(black);
    }

    @Override
    public ArrayList<int[]> possibleMoves(){
        //method for pawn take because only piece with different take vs regular move
        return new ArrayList<int[]>();
    }

    public int specialMove(){
        return 0;
    }


    public int pawnTake(){
        return 0;
    }
    @Override
    public Piece newCopy() {
        return null;
    }
}

