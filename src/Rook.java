public class Rook extends Piece{

    public Rook(int moveLocation){
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

    @Override
    public Piece newCopy() {
        return null;
    }
}
