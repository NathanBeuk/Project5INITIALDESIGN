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

        String piece = currentBoard[row][column];
        if (currentBoard[row][column] != ""){// R = rook, H = horse(knight), B = Bishop, Q = queen, K = king, P = pawn, first column will just be command lines
            if (piece.charAt(0) == 'P'){
                possibleMoves.addAll(pawnPossibleMoves(row,column,currentBoard));
            }
            else if (piece.charAt(0) == 'R'){

            }
            else if (piece.charAt(0) == 'H'){

            }
            else if (piece.charAt(0) == 'B'){

            }
            else if (piece.charAt(0) == 'Q'){

            }
            else if (piece.charAt(0) == 'K'){

            }


        }

        return possibleMoves;
    }
    public ArrayList<int[]> pawnPossibleMoves(int row, int column, String[][] currentBoard){
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        //TODO en passant later
        if (currentBoard[row - 1][column].equals("")){//position directly infront is empty and therefore pawn can move
            if (validPosition(row-1, column, currentBoard)){
                possibleMoves.add(position(row-1,column));
            }
        }
        if (enemy(currentBoard[row][column],currentBoard[row - 1][column - 1])){
            if (validPosition(row-1, column - 1, currentBoard)){
                possibleMoves.add(position(row - 1,column - 1));
            }

        }
        if (enemy(currentBoard[row][column],currentBoard[row - 1][column + 1])){
            if (validPosition(row-1, column + 1, currentBoard)){
                possibleMoves.add(position(row - 1,column + 1));
            }

        }
        return possibleMoves;
    }
    public ArrayList<int[]> rookPossibleMoves(int row, int column, String[][] currentBoard){
        return new ArrayList<>();
    }
    public boolean validPosition(int row, int column, String[][] board){
        int numRows = board.length;
        int numColumns = board[0].length;
        if (row >= 0 && row < numRows){
            if (column > 0 && column < numColumns){//columns are nine across so valid moves can't be equal to zero (command lines)
                return true;
            }
        }
        return false;
    }
    public boolean enemy(String movingPiece, String possibleEnemy){
        if (movingPiece.length() == 0 || possibleEnemy.length() == 0 || movingPiece.length() > 0 && possibleEnemy.length() > 0 && movingPiece.charAt(2) == possibleEnemy.charAt(2)){//needs to check if empty string before trying to get any chars from it
            return false;//not an enemy
        }
        return true;//is an enemy
    }
    public int[] position(int row, int column){
        int[] pos = {row, column};
        return pos;
    }
    public ArrayList<int[]> getPossibleDiagonalMoves(int row, int column, String[][] currentBoard){
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




}
