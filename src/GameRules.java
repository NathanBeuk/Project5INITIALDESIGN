import java.util.ArrayList;

/**
 * Description
 * @author Adoniram Courser and Nathan Beukema
 * @version 1.0
 * @since 11-29-23
 */
public class GameRules {







    /**
     * constructor
     */
    public GameRules(){

    }

    public ArrayList<int[]> getPossibleMoves(int row, int column, String[][] currentBoard){
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        /*
        if black
            if rook
            if knight
            if bishop
            if queen
            if king
            if pawn

        else if white
            if rook
            if knight
            if bishop
            if queen
            if king
            if pawn
         */
        return possibleMoves;
    }
    public String[][] flipBoard(String[][] board){//will be useful to simplyfy operations
        int rows = board.length;
        int columns = board[0].length;

        for (int i = 0; i < rows; i = i + 1){
            for (int j = 1; j <= columns/2; j = j + 1){

                String temp = board[i][j];
                board[i][j] = board[rows - 1 - i][columns - j];
                board[rows - 1 - i][columns - j] = temp;


            }
        }
        return board;
    }

    public ArrayList<int[]> getPossibleDiagonalMoves(int row, int column, String[][] currentBoard, String pieceType){
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        /*
        if pieceType == "King"
        else if pieceType == "Pawn"
        else if pieceType == "Queen" || pieceType == "Bishop"
        else
            return empty
         */
        return possibleMoves;
    }


}
