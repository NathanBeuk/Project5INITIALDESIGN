public abstract class Piece {
    int moveLocation;

    public Piece(int moveLocation){
        this.moveLocation = moveLocation;
    }

    public abstract int move();

    public abstract boolean isTaken();

    public abstract Piece newCopy();


}
