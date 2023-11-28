public class King extends Piece{

    public King(int moveLocation){
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

    // for castling either king or queenside. Will need to incorporate Rook class
    public int specialMove(){
        return 0;
    }

    @Override
    public Piece newCopy() {
        return null;
    }
}
