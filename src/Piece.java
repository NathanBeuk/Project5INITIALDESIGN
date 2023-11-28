import java.util.ArrayList;

public abstract class Piece {
    private boolean black;

    public Piece(boolean black){
        this.black = black;
    }

    public boolean move(int x, int y){
        ArrayList<int[]> possibleMoves = possibleMoves();
        boolean validMove = false;
        for (int i = 0; i < possibleMoves.size();i = i + 1){
            if (x == possibleMoves.get(i)[0] && y == possibleMoves.get(i)[1]){
                validMove = true;
            }
        }
        return validMove;
    }
    public abstract ArrayList<int[]>possibleMoves();



    public abstract Piece newCopy();


}
