import java.util.ArrayList;

public class Pawn extends Piece{

    public Pawn(boolean black){
        super(black);
    }

    @Override
    public ArrayList<int[]> possibleMoves(){

        return new ArrayList<int[]>();
    }

    public int specialMove(){
        return 0;
    }

    //method for pawn take because only piece with different take vs regular move
    public int pawnTake(){
        return 0;
    }
    @Override
    public Piece newCopy() {
        return null;
    }
}

