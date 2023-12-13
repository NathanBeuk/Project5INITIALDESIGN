import java.util.ArrayList;
import java.util.Queue;

/**
 * Description
 *
 * @author Adoniram Courser and Nathan Beukema
 * @version 1.0
 * @since 11-29-23
 */
public class GameRules {
    /**
     * constructor
     */
    public GameRules() {

    }


    public ArrayList<int[]> getPossibleMoves(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        String piece = currentBoard[row][column];
        if (currentBoard[row][column] != "") {// R = rook, H = horse(knight), B = Bishop, Q = queen, K = king, P = pawn, first column will just be command lines
            if (piece.charAt(0) == 'P') {
                possibleMoves.addAll(pawnPossibleMoves(row, column, currentBoard));
            } else if (piece.charAt(0) == 'R') {
                possibleMoves.addAll(rookPossibleMoves(row, column, currentBoard));
            } else if (piece.charAt(0) == 'H') {
                possibleMoves.addAll(horsePossibleMoves(row, column, currentBoard));
            } else if (piece.charAt(0) == 'B') {
                possibleMoves.addAll(bishopPossibleMovesDR(row, column, currentBoard));
                possibleMoves.addAll(bishopPossibleMovesDL(row, column ,currentBoard));
                possibleMoves.addAll(bishopPossibleMovesUR(row, column ,currentBoard));
                possibleMoves.addAll(bishopPossibleMovesUL(row, column, currentBoard));





            } else if (piece.charAt(0) == 'Q') {
                possibleMoves.addAll(queenPossibleMoves(row, column, currentBoard));

                possibleMoves.addAll(queenPossibleMovesDR(row, column, currentBoard));
                possibleMoves.addAll(queenPossibleMovesDL(row, column ,currentBoard));
                possibleMoves.addAll(queenPossibleMovesUR(row, column ,currentBoard));
                possibleMoves.addAll(queenPossibleMovesUL(row, column, currentBoard));


            } else if (piece.charAt(0) == 'K') {
                possibleMoves.addAll(kingPossibleMoves(row, column, currentBoard));
            }
        }
        return possibleMoves;
    }

    public boolean pawnToQueen(int row, String piece){
        if(row == 0 && piece.charAt(0) == 'P'){
            return true;
        }
        return false;
    }




    public boolean isEmptyTile(int row, int column, String[][] currentBoard){
        if (currentBoard[row][column].equals(""))
            return true;
        return false;
    }
    public boolean enemy(String movingPiece, String possibleEnemy) {
        if (movingPiece.length() == 0 || possibleEnemy.length() == 0 || movingPiece.length() > 0 && possibleEnemy.length() > 0 && movingPiece.charAt(2) == possibleEnemy.charAt(2)) {//needs to check if empty string before trying to get any chars from it
            return false;//not an enemy
        }
        return true;//is an enemy
    }

    public boolean ally(String movingPiece, String possibleAlly) {
        if (movingPiece.isEmpty() || possibleAlly.isEmpty() || !movingPiece.isEmpty() && !possibleAlly.isEmpty() && movingPiece.charAt(2) != possibleAlly.charAt(2)) {//needs to check if empty string before trying to get any chars from it
            return false; // not a friendly
        }
        return true;//is an ally
    }
    public boolean validPosition(int row, int column, String[][] board) {
        int numRows = board.length;
        int numColumns = board[0].length;
        if (row >= 0 && row < numRows) {
            if (column > 0 && column < numColumns) {//columns are nine across so valid moves can't be equal to zero (command lines)
                return true;
            }
        }
        return false;
    }



    public int[] position(int row, int column) {
        int[] pos = {row, column};
        return pos;
    }
    public ArrayList<int[]> bishopPossibleMovesDR(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        //for every increment of both variables, you can set i = to either starting row index or column index
        //this is effectively the same regardless of which you choose, because you just need a starting reference point.
        //incrementing both values moves the value horizontally and vertically by one, making the diagonal movement.

        boolean condition = true;
        int i = 1;
        while (condition){
            if(validPosition(row + i, column + i, currentBoard)){
                if (isEmptyTile(row + i,column + i, currentBoard) || enemy(currentBoard[row][column], currentBoard[row + i][column + i])){
                    possibleMoves.add(position(row + i, column + i));
                    if(enemy(currentBoard[row][column], currentBoard[row + i][column + i])){
                        condition = false;
                    }

                }
                else {
                    condition = false;
                }
            }
            else {
                condition = false;
            }


            i = i + 1;
        }

        return possibleMoves;
    }

    public ArrayList<int[]> bishopPossibleMovesUR(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        boolean condition = true;
        int i = 1;
        while (condition){
            if(validPosition(row - i, column + i, currentBoard)){
                if (isEmptyTile(row - i,column + i, currentBoard) || enemy(currentBoard[row][column], currentBoard[row - i][column + i])){
                    possibleMoves.add(position(row - i, column + i));
                    if(enemy(currentBoard[row][column], currentBoard[row - i][column + i])){
                        condition = false;
                    }

                }
                else {
                    condition = false;
                }
            }
            else {
                condition = false;
            }


            i = i + 1;
        }

        return possibleMoves;


    }

    public ArrayList<int[]> bishopPossibleMovesDL(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        boolean condition = true;
        int i = 1;
        while (condition){
            if(validPosition(row + i, column - i, currentBoard)){
                if (isEmptyTile(row + i,column - i, currentBoard) || enemy(currentBoard[row][column], currentBoard[row + i][column - i])){
                    possibleMoves.add(position(row + i, column - i));
                    if(enemy(currentBoard[row][column], currentBoard[row + i][column - i])){
                        condition = false;
                    }
                }
                else {
                    condition = false;
                }
            }
            else {
                condition = false;
            }


            i = i + 1;
        }

        return possibleMoves;

    }

    public ArrayList<int[]> bishopPossibleMovesUL(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        boolean condition = true;
        int i = 1;
        while (condition){
            if(validPosition(row - i, column - i, currentBoard)){
                if (isEmptyTile(row - i,column - i, currentBoard) || enemy(currentBoard[row][column], currentBoard[row - i][column - i])){
                    possibleMoves.add(position(row - i, column - i));
                    if(enemy(currentBoard[row][column], currentBoard[row - i][column - i])){
                        condition = false;
                    }

                }
                else {
                    condition = false;
                }
            }
            else {
                condition = false;
            }


            i = i + 1;
        }

        return possibleMoves;
    }














    public ArrayList<int[]> queenPossibleMovesDR(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> QUEENpossibleMovesDR = new ArrayList<>();

        //for every increment of both variables, you can set i = to either starting row index or column index
        //this is effectively the same regardless of which you choose, because you just need a starting reference point.
        //incrementing both values moves the value horizontally and vertically by one, making the diagonal movement.

        row = row + 1; column = column + 1;
        for (int i = column; i < currentBoard.length; i++, row++) { // down right
            if (ally(currentBoard[row][column], currentBoard[row][i])) {
                break;
            }
            if (enemy(currentBoard[row][column], currentBoard[row][i])) {
                QUEENpossibleMovesDR.add(position(row, i));
                break;
            }
            if (validPosition(row, i, currentBoard)) {
                QUEENpossibleMovesDR.add(position(row, i));
            }
        }

        return QUEENpossibleMovesDR;
    }

    public ArrayList<int[]> queenPossibleMovesUR(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> QUEENpossibleMovesUR = new ArrayList<>();

        row = row - 1; column = column + 1;
        for (int i = column; i < currentBoard[0].length; i++, row--) { // up right
            if (ally(currentBoard[row][column], currentBoard[row][i])) {
                break;
            }
            if (enemy(currentBoard[row][column], currentBoard[row][i])) {
                QUEENpossibleMovesUR.add(position(row, i));
                break;
            }
            if (validPosition(row, i, currentBoard)) {
                QUEENpossibleMovesUR.add(position(row, i));
            }
        }

        return QUEENpossibleMovesUR;
    }

    public ArrayList<int[]> queenPossibleMovesDL(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> QUEENpossibleMovesDL = new ArrayList<>();
        row = row + 1; column = column - 1;
        for (int i = column; i > 0; i--, row++) { // down left
            if (ally(currentBoard[row][column], currentBoard[row][i])) {
                break;
            }
            if (enemy(currentBoard[row][column], currentBoard[row][i])) {
                QUEENpossibleMovesDL.add(position(row, i));
                break;
            }
            if (validPosition(row, i, currentBoard)) {
                QUEENpossibleMovesDL.add(position(row, i));
            }
        }

        return QUEENpossibleMovesDL;
    }

    public ArrayList<int[]> queenPossibleMovesUL(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> QUEENpossibleMovesUL = new ArrayList<>();
        row = row - 1; column = column - 1;
        for (int i = column; i > 0; i--, row--) { //up left
            if (ally(currentBoard[row][column], currentBoard[row][i])) {
                break;
            }
            if (enemy(currentBoard[row][column], currentBoard[row][i])) {
                QUEENpossibleMovesUL.add(position(row, i));
                break;
            }
            if (validPosition(row, i, currentBoard)) {
                QUEENpossibleMovesUL.add(position(row, i));
            }
        }

        return QUEENpossibleMovesUL;
    }





















    public ArrayList<int[]> pawnPossibleMoves(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> PAWNpossibleMoves = new ArrayList<>();
        //TODO en passant later
        if (validPosition(row - 1, column, currentBoard)) {//position directly infront is empty and therefore pawn can move
            if (currentBoard[row - 1][column].equals("")) {
                if (!ally(currentBoard[row][column], currentBoard[row - 1][column])) {
                    PAWNpossibleMoves.add(position(row - 1, column));
                }
            }
        }
        if (validPosition(row - 1, column - 1, currentBoard)) {
            if (enemy(currentBoard[row][column], currentBoard[row - 1][column - 1])) {
                if (!ally(currentBoard[row][column], currentBoard[row - 1][column - 1])) {
                    PAWNpossibleMoves.add(position(row - 1, column - 1));
                }
            }
        }
        if (validPosition(row - 1, column + 1, currentBoard)) {
            if (enemy(currentBoard[row][column], currentBoard[row - 1][column + 1])) {
                if (!ally(currentBoard[row][column], currentBoard[row - 1][column + 1])) {
                    PAWNpossibleMoves.add(position(row - 1, column + 1));
                }
            }
        }


        return PAWNpossibleMoves;
    }









    public ArrayList<int[]> rookPossibleMoves(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> ROOKpossiblemoves = new ArrayList<>();

        for (int i = row - 1; i >= 0; i--) { //up
            if (ally(currentBoard[row][column], currentBoard[i][column])) {
                break;
            }
            if (enemy(currentBoard[row][column], currentBoard[i][column])) {
                ROOKpossiblemoves.add(position(i, column));
                break;
            }
            if (validPosition(i, column, currentBoard)) {
                ROOKpossiblemoves.add(position(i, column));
            }
        }

        for (int i = column - 1; i > 0; i--) { // left
            if (ally(currentBoard[row][column], currentBoard[row][i])) {
                break;
            }
            if (enemy(currentBoard[row][column], currentBoard[row][i])) {
                ROOKpossiblemoves.add(position(row, i));
                break;
            }
            if (validPosition(row, i, currentBoard)) {
                ROOKpossiblemoves.add(position(row, i));
            }
        }

        for (int i = row + 1; i < currentBoard.length; i++) { //down
            if (ally(currentBoard[row][column], currentBoard[i][column])) {
                break;
            }
            if (enemy(currentBoard[row][column], currentBoard[i][column])) {
                ROOKpossiblemoves.add(position(i, column));
                break;
            }
            if (validPosition(i, column, currentBoard)) {
                ROOKpossiblemoves.add(position(i, column));
            }
        }

        for (int i = column + 1; i < currentBoard[0].length; i++) { // right
            if (ally(currentBoard[row][column], currentBoard[row][i])) {
                break;
            }
            if (enemy(currentBoard[row][column], currentBoard[row][i])) {
                ROOKpossiblemoves.add(position(row, i));
                break;
            }
            if (validPosition(row, i, currentBoard)) {
                ROOKpossiblemoves.add(position(row, i));
            }
        }

        return ROOKpossiblemoves;
    }














    //hardcoded moves for knight since there's only a max number of 8, and it is less dynamic
    public ArrayList<int[]> horsePossibleMoves(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> HORSEpossibleMoves = new ArrayList<>();

        if (validPosition(row - 2, column - 1, currentBoard)) {
            if (!ally(currentBoard[row][column], currentBoard[row - 2][column - 1])) {
                HORSEpossibleMoves.add(position(row - 2, column - 1));
            }
        }
        if (validPosition(row - 2, column + 1, currentBoard)) {
            if (!ally(currentBoard[row][column], currentBoard[row - 2][column + 1])) {
                HORSEpossibleMoves.add(position(row - 2, column + 1));
            }
        }
        if (validPosition(row + 2, column - 1, currentBoard)) {
            if (!ally(currentBoard[row][column], currentBoard[row + 2][column - 1])) {
                HORSEpossibleMoves.add(position(row + 2, column - 1));
            }
        }
        if (validPosition(row + 2, column + 1, currentBoard)) {
            if (!ally(currentBoard[row][column], currentBoard[row + 2][column + 1])) {
                HORSEpossibleMoves.add(position(row + 2, column + 1));
            }
        }
        if (validPosition(row - 1, column - 2, currentBoard)) {
            if (!ally(currentBoard[row][column], currentBoard[row - 1][column - 2])) {
                HORSEpossibleMoves.add(position(row - 1, column - 2));
            }
        }
        if (validPosition(row - 1, column + 2, currentBoard)) {
            if (!ally(currentBoard[row][column], currentBoard[row - 1][column + 2])) {
                HORSEpossibleMoves.add(position(row - 1, column + 2));
            }
        }
        if (validPosition(row + 1, column - 2, currentBoard)) {
            if (!ally(currentBoard[row][column], currentBoard[row + 1][column - 2])) {
                HORSEpossibleMoves.add(position(row + 1, column - 2));
            }
        }
        if (validPosition(row + 1, column + 2, currentBoard)) {
            if (!ally(currentBoard[row][column], currentBoard[row + 1][column + 2])) {
                HORSEpossibleMoves.add(position(row + 1, column + 2));
            }
        }

        return HORSEpossibleMoves;
    }














    public ArrayList<int[]> queenPossibleMoves(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> QUEENpossibleMoves = new ArrayList<>();

        // VERTICAL and HORIZONTAL movement
        for (int i = row - 1; i >= 0; i--) { //up
            if (ally(currentBoard[row][column], currentBoard[i][column])) {
                break;
            }
            if (enemy(currentBoard[row][column], currentBoard[i][column])) {
                QUEENpossibleMoves.add(position(i, column));
                break;
            }
            if (validPosition(i, column, currentBoard)) {
                QUEENpossibleMoves.add(position(i, column));
            }
        }

        for (int i = column - 1; i > 0; i--) { // left
            if (ally(currentBoard[row][column], currentBoard[row][i])) {
                break;
            }
            if (enemy(currentBoard[row][column], currentBoard[row][i])) {
                QUEENpossibleMoves.add(position(row, i));
                break;
            }
            if (validPosition(row, i, currentBoard)) {
                QUEENpossibleMoves.add(position(row, i));
            }
        }

        for (int i = row + 1; i < currentBoard.length; i++) { //down
            if (ally(currentBoard[row][column], currentBoard[i][column])) {
                break;
            }
            if (enemy(currentBoard[row][column], currentBoard[i][column])) {
                QUEENpossibleMoves.add(position(i, column));
                break;
            }
            if (validPosition(i, column, currentBoard)) {
                QUEENpossibleMoves.add(position(i, column));
            }
        }

        for (int i = column + 1; i < currentBoard[0].length; i++) { // right
            if (ally(currentBoard[row][column], currentBoard[row][i])) {
                break;
            }
            if (enemy(currentBoard[row][column], currentBoard[row][i])) {
                QUEENpossibleMoves.add(position(row, i));
                break;
            }
            if (validPosition(row, i, currentBoard)) {
                QUEENpossibleMoves.add(position(row, i));
            }
        }

        return QUEENpossibleMoves;
    }














































    //hardcoded moves for king as well since only max of 8, and less dynamic
    public ArrayList<int[]> kingPossibleMoves(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> KINGpossibleMoves = new ArrayList<>();

        if (validPosition(row - 1, column - 1, currentBoard)) {
            if (!ally(currentBoard[row][column], currentBoard[row - 1][column - 1])) {
                KINGpossibleMoves.add(position(row - 1, column - 1));
            }
        }
        if (validPosition(row, column - 1, currentBoard)) {
            if (!ally(currentBoard[row][column], currentBoard[row][column - 1])) {
                KINGpossibleMoves.add(position(row, column - 1));
            }
        }
        if (validPosition(row + 1, column - 1, currentBoard)) {
            if (!ally(currentBoard[row][column], currentBoard[row + 1][column - 1])) {
                KINGpossibleMoves.add(position(row + 1, column - 1));
            }
        }
        if (validPosition(row - 1, column, currentBoard)) {
            if (!ally(currentBoard[row][column], currentBoard[row - 1][column])) {
                KINGpossibleMoves.add(position(row - 1, column));
            }
        }
        if (validPosition(row + 1, column, currentBoard)) {
            if (!ally(currentBoard[row][column], currentBoard[row + 1][column])) {
                KINGpossibleMoves.add(position(row + 1, column));
            }
        }
        if (validPosition(row - 1, column + 1, currentBoard)) {
            if (!ally(currentBoard[row][column], currentBoard[row - 1][column + 1])) {
                KINGpossibleMoves.add(position(row - 1, column + 1));
            }
        }
        if (validPosition(row, column + 1, currentBoard)) {
            if (!ally(currentBoard[row][column], currentBoard[row][column + 1])) {
                KINGpossibleMoves.add(position(row, column + 1));
            }
        }
        if (validPosition(row + 1, column + 1, currentBoard)) {
            if (!ally(currentBoard[row][column], currentBoard[row + 1][column + 1])) {
                KINGpossibleMoves.add(position(row + 1, column + 1));
            }
        }
        return KINGpossibleMoves;
    }





    /*
        //TODO Changed this
        public boolean enemy(String movingPiece, String possibleEnemy){
            if(!possibleEnemy.isEmpty() && (movingPiece.charAt(2) == possibleEnemy.charAt(2))){
                return true;
            }
            return false;
        }

     */

    public ArrayList<int[]> getPossibleDiagonalMoves(int row, int column, String[][] currentBoard) {
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

    public String[][] flipBoard(String[][] board) {//will be useful to simplify operations
        int rows = board.length;
        int columns = board[0].length;

        for (int i = 0; i < rows; i = i + 1) {
            for (int j = 1; j <= columns / 2; j = j + 1) {

                String temp = board[i][j];
                board[i][j] = board[rows - 1 - i][columns - j];
                board[rows - 1 - i][columns - j] = temp;
            }
        }
        return board;
    }
}