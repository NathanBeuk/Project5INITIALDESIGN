import java.util.ArrayList;

/**
 * Description
 *
 * @author Adoniram Courser and Nathan Beukema
 * @version 1.0
 * @since 11-29-23
 */
public class GameRules {
    /**
     * Constructor
     */
    public GameRules() {

    }


    /**
     * a method that returns all possible move for a selected tile on the current board
     * @param row: row of selected position
     * @param column: column of selected position
     * @param currentBoard: the current board
     * @return ArrayList<int[]> all possible moves for a piece that is selected, arrayList is empty if position has no possible moves
     */
    public ArrayList<int[]> getPossibleMoves(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        String piece = currentBoard[row][column];
        if (currentBoard[row][column] != "") {// R = rook, H = horse(knight), B = Bishop, Q = queen, K = king, P = pawn, first column will just be command lines
            if (piece.charAt(0) == 'P') {
                possibleMoves.addAll(pawnPossibleMoves(row, column, currentBoard));
            } else if (piece.charAt(0) == 'R') {
                possibleMoves.addAll(getPossibleHorizontalAndVerticalMoves(row, column, currentBoard));
            } else if (piece.charAt(0) == 'H') {
                possibleMoves.addAll(horsePossibleMoves(row, column, currentBoard));
            } else if (piece.charAt(0) == 'B') {
                possibleMoves.addAll(getPossibleDiagonalMoves(row, column, currentBoard));

            } else if (piece.charAt(0) == 'Q') {
                possibleMoves.addAll(getPossibleHorizontalAndVerticalMoves(row, column, currentBoard));

                possibleMoves.addAll(getPossibleDiagonalMoves(row, column, currentBoard));

            } else if (piece.charAt(0) == 'K') {
                possibleMoves.addAll(kingPossibleMoves(row, column, currentBoard));
            }
        }
        return possibleMoves;
    }

    /**
     * a method for the logic on whether to transform a pawn into a queen
     * @param row: row of selected position
     * @param piece: column of selected position
     * @return a boolean on whether a pawn should be promoted to a queen
     */
    public boolean pawnToQueen(int row, String piece) {
        if (row == 0 && piece.charAt(0) == 'P') {
            return true;
        }
        return false;
    }

    /**
     *
     * @param row: row of selected position
     * @param column: column of selected position
     * @param currentBoard: the current board
     * @return a boolean on whether a position is an empty tile
     */
    public boolean isEmptyTile(int row, int column, String[][] currentBoard) {
        if (currentBoard[row][column].equals(""))
            return true;
        return false;
    }

    /**
     *
     * @param movingPiece: the piece selected
     * @param possibleEnemy: the piece at the tile being checked
     * @return a boolean on whether the piece on a tile is an enemy
     */
    public boolean enemy(String movingPiece, String possibleEnemy) {
        if (movingPiece.length() == 0 || possibleEnemy.length() == 0 || movingPiece.charAt(2) == possibleEnemy.charAt(2)) {//needs to check if empty string before trying to get any chars from it

            return false;//not an enemy
        }
        return true;//is an enemy
    }

    /**
     *
     * @param movingPiece: the piece selected
     * @param possibleAlly: the piece at the tile being checked
     * @return used in the same way as combining the isEmptyTile method and enemy tile for faster use
     */
    public boolean ally(String movingPiece, String possibleAlly) {
        if (movingPiece.isEmpty() || possibleAlly.isEmpty() || !movingPiece.isEmpty() && !possibleAlly.isEmpty() && movingPiece.charAt(2) != possibleAlly.charAt(2)) {//needs to check if empty string before trying to get any chars from it
            return false; // not a friendly
        }
        return true;//is an ally
    }

    /**
     *
     * @param row: row of selected position
     * @param column: column of selected position
     * @param currentBoard: the current board
     * @return a boolean on whether a row and column is a valid position on the board
     */
    public boolean validPosition(int row, int column, String[][] currentBoard) {
        int numRows = currentBoard.length;
        int numColumns = currentBoard[0].length;
        if (row >= 0 && row < numRows) {
            if (column > 0 && column < numColumns) {//columns are nine across so valid moves can't be equal to zero (command lines)
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param row: row of a position
     * @param column: column of a position
     * @return an int[] two element array for a given row and column
     */
    public int[] position(int row, int column) {
        int[] pos = {row, column};
        return pos;
    }


    //pawn moves

    /**
     *
     * @param row
     * @param column
     * @param currentBoard
     * @return
     */
    public ArrayList<int[]> pawnPossibleMoves(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> PAWNpossibleMoves = new ArrayList<>();
        //TODO en passant later
        boolean directlyInFront = false;

        if (validPosition(row - 1, column, currentBoard)) {//position directly infront is empty and therefore pawn can move
            if (isEmptyTile(row - 1,column, currentBoard)) {//needs to be emtpy
                PAWNpossibleMoves.add(position(row - 1, column));
                directlyInFront = true;

            }
        }
        if (validPosition(row - 1, column - 1, currentBoard)) {//forward and to the left

            if (!isEmptyTile(row - 1,column - 1, currentBoard)) {//can't be empty

                if (enemy(currentBoard[row][column], currentBoard[row - 1][column - 1])) {

                    PAWNpossibleMoves.add(position(row - 1, column - 1));
                }
            }
        }
        if (validPosition(row - 1, column + 1, currentBoard)) {//forward and to right
            if (!isEmptyTile(row - 1, column + 1, currentBoard)) {
                if (enemy(currentBoard[row][column], currentBoard[row - 1][column + 1])) {
                    PAWNpossibleMoves.add(position(row - 1, column + 1));
                }
            }
        }

        //String movingPiece = currentBoard[lastSelectedPosition[0]][lastSelectedPosition[1]];
        //int timesMoved = Integer.parseInt(movingPiece.substring(3)) + 1;
        //movingPiece = movingPiece.substring(0,3) + timesMoved;
        if (Integer.parseInt(currentBoard[row][column].substring(3)) == 0) {
            if (validPosition(row - 2, column, currentBoard)) {
                if(directlyInFront){
                    if (isEmptyTile(row - 2, column, currentBoard)) {
                        PAWNpossibleMoves.add(position(row - 2, column));
                    }
                }
            }
        }
        return PAWNpossibleMoves;
    }

    //hardcoded moves for knight since there's only a max number of 8, and it is less dynamic
    //Rook moves

    /**
     *
     * @param row
     * @param column
     * @param currentBoard
     * @return
     */
    public ArrayList<int[]> horsePossibleMoves(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> HORSEpossibleMoves = new ArrayList<>();

        if (validPosition(row - 2, column - 1, currentBoard)) {
            if (isEmptyTile(row - 2, column - 1,currentBoard) || enemy(currentBoard[row][column], currentBoard[row - 2][column - 1])){
                HORSEpossibleMoves.add(position(row - 2, column - 1));
            }
        }
        if (validPosition(row - 2, column + 1, currentBoard)) {
            if(isEmptyTile(row - 2, column + 1, currentBoard) || enemy(currentBoard[row][column], currentBoard[row - 2][column + 1])){
                HORSEpossibleMoves.add(position(row - 2, column + 1));
            }
        }
        if (validPosition(row + 2, column - 1, currentBoard)) {
            if(isEmptyTile(row + 2, column - 1, currentBoard) || enemy(currentBoard[row][column], currentBoard[row + 2][column - 1])){
                HORSEpossibleMoves.add(position(row + 2, column - 1));
            }
        }
        if (validPosition(row + 2, column + 1, currentBoard)) {
            if (isEmptyTile(row + 2, column + 1, currentBoard) || enemy(currentBoard[row][column], currentBoard[row + 2][column + 1])){
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

    //hardcoded moves for king as well since only max of 8, and less dynamic

    /**
     *
     * @param row
     * @param column
     * @param currentBoard
     * @return
     */
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


    /**
     *
     * @param board
     * @return
     */
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


    /**
     *
     * @param row
     * @param column
     * @param currentBoard
     * @return
     */
    public ArrayList<int[]> getPossibleDiagonalMoves(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        boolean condition = true;
        boolean downRight = true;
        boolean upRight = true;
        boolean downLeft = true;
        boolean upLeft = true;


        int i = 1;
        while (condition) {
            //down and to the right
            if (downRight){
                if (validPosition(row + i, column + i, currentBoard)) {
                    if (isEmptyTile(row + i, column + i, currentBoard) || enemy(currentBoard[row][column], currentBoard[row + i][column + i])) {
                        possibleMoves.add(position(row + i, column + i));
                        if (enemy(currentBoard[row][column], currentBoard[row + i][column + i])) {
                            downRight = false;
                        }
                    } else {
                        downRight = false;
                    }
                } else {
                    downRight = false;
                }
            }


            //up and to the right
            if (upRight){
                if (validPosition(row - i, column + i, currentBoard)) {
                    if (isEmptyTile(row - i, column + i, currentBoard) || enemy(currentBoard[row][column], currentBoard[row - i][column + i])) {
                        possibleMoves.add(position(row - i, column + i));
                        if (enemy(currentBoard[row][column], currentBoard[row - i][column + i])) {
                            upRight = false;
                        }
                    } else {
                        upRight = false;
                    }
                } else {
                    upRight = false;
                }
            }

            //down and to the left
            if (downLeft){
                if (validPosition(row + i, column - i, currentBoard)) {
                    if (isEmptyTile(row + i, column - i, currentBoard) || enemy(currentBoard[row][column], currentBoard[row + i][column - i])) {
                        possibleMoves.add(position(row + i, column - i));
                        if (enemy(currentBoard[row][column], currentBoard[row + i][column - i])) {
                            downLeft = false;
                        }
                    } else {
                        downLeft = false;
                    }
                } else {
                    downLeft = false;
                }
            }




            //up and to the left
            if (upLeft){
                if (validPosition(row - i, column - i, currentBoard)) {
                    if (isEmptyTile(row - i, column - i, currentBoard) || enemy(currentBoard[row][column], currentBoard[row - i][column - i])) {
                        possibleMoves.add(position(row - i, column - i));
                        if (enemy(currentBoard[row][column], currentBoard[row - i][column - i])) {
                            upLeft = false;
                        }
                    } else {
                        upLeft = false;
                    }
                } else {
                    upLeft = false;
                }
            }



            //check condition
            if (!downRight){//block to end code
                if(!upRight){
                    if(!downLeft){
                        if(!upLeft){
                            condition = false;
                        }
                    }
                }
            }
            i = i + 1;
        }
        return possibleMoves;
    }

    /**
     *
     * @param row
     * @param column
     * @param currentBoard
     * @return
     */
    public ArrayList<int[]> getPossibleHorizontalAndVerticalMoves(int row, int column, String[][] currentBoard){
        ArrayList<int[]> possibleMoves = new ArrayList<>();


        for (int i = row - 1; i >= 0; i--) { //up
            if (validPosition(i, column, currentBoard)){
                if (isEmptyTile(i,column,currentBoard) || enemy(currentBoard[row][column], currentBoard[i][column])){
                    //if tile is either empty or there is an enemy on the tile
                    possibleMoves.add(position(i, column));
                    if (enemy(currentBoard[row][column], currentBoard[i][column])) {
                        break;
                    }
                }
                else {//tile has something on it and it isn't an enemy
                    break;
                }
            }
            else {//if not a valid position
                break;
            }
        }

        for (int i = column - 1; i > 0; i--) { // left
            if (validPosition(row, i, currentBoard)){
                if (isEmptyTile(row, i, currentBoard) || enemy(currentBoard[row][column], currentBoard[row][i])){
                    //if tile is either empty or there is an enemy on the tile
                    possibleMoves.add(position(row, i));
                    if (enemy(currentBoard[row][column], currentBoard[row][i])) {
                        break;
                    }
                }
                else {//tile has something on it and it isn't an enemy
                    break;
                }
            }
            else {//if not a valid position
                break;
            }
        }

        for (int i = row + 1; i < currentBoard.length; i++) { //down
            if (validPosition(i, column, currentBoard)){
                if (isEmptyTile(i,column,currentBoard) || enemy(currentBoard[row][column], currentBoard[i][column])){
                    //if tile is either empty or there is an enemy on the tile
                    possibleMoves.add(position(i, column));
                    if (enemy(currentBoard[row][column], currentBoard[i][column])) {
                        break;
                    }
                }
                else {//tile has something on it and it isn't an enemy
                    break;
                }
            }
            else {//if not a valid position
                break;
            }
        }

        for (int i = column + 1; i < currentBoard[0].length; i++) { // right
            if (validPosition(row, i, currentBoard)){
                if (isEmptyTile(row, i, currentBoard) || enemy(currentBoard[row][column], currentBoard[row][i])){
                    //if tile is either empty or there is an enemy on the tile
                    possibleMoves.add(position(row, i));
                    if (enemy(currentBoard[row][column], currentBoard[row][i])) {
                        break;
                    }
                }
                else {//tile has something on it and it isn't an enemy
                    break;
                }
            }
            else {//if not a valid position
                break;
            }
        }

        return possibleMoves;
    }

}