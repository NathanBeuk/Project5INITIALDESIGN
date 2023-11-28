public class Knight extends Piece{

    public Knight(int moveLocation){
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
