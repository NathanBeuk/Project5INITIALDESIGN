public class Pawn extends Piece{

    public Pawn(int moveLocation){
        super(moveLocation);
    }

    @Override
    public int move() {
        return 0;
    }

    @Override
    public boolean isTaken() {
        return false;
    }

    public int specialMove(){ //for two space first move
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

