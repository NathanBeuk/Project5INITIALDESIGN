import java.util.ArrayList;

/**
 * This class holds all code for piece function for the chess game. Every piece's possible moves are recorded here, and given to the board class.
 * Pieces are stored in terms of their location on the board, and their reference to their location relative to other pieces dictate their ability to move.
 *
 * @author Adoniram Courser and Nathan Beukema
 * @version 1.0
 * @since 12-13-23
 */
public class GameRules {
    /**
     * Constructor
     */
    public GameRules() {

    }

    /**
     * @param row, row of selected position
     * @param column, column of selected position
     * @param currentBoard, takes current board
     * @return all possible moves for a piece that is selected, arrayList is empty if position has no possible moves
     */
    public ArrayList<int[]> getPossibleMoves(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        String piece = currentBoard[row][column];
        if (currentBoard[row][column] != "") {// R = rook, H = horse(knight), B = Bishop, Q = queen, K = king, P = pawn, first column will just be command lines
            if (piece.charAt(0) == 'P') {
                possibleMoves.addAll(pawnPossibleMoves(row, column, currentBoard));
            } else if (piece.charAt(0) == 'R') {
                possibleMoves.addAll(possibleHorizontalAndVerticalMoves(row, column, currentBoard));
            } else if (piece.charAt(0) == 'H') {
                possibleMoves.addAll(horsePossibleMoves(row, column, currentBoard));
            } else if (piece.charAt(0) == 'B') {
                possibleMoves.addAll(getPossibleDiagonalMoves(row, column, currentBoard));

            } else if (piece.charAt(0) == 'Q') {
                possibleMoves.addAll(possibleHorizontalAndVerticalMoves(row, column, currentBoard));

                possibleMoves.addAll(getPossibleDiagonalMoves(row, column, currentBoard));

            } else if (piece.charAt(0) == 'K') {
                possibleMoves.addAll(kingPossibleMoves(row, column, currentBoard));
            }
        }
        return possibleMoves;
    }

    /**
     * @param row   index of row location on board for potential piece change
     * @param piece that is being changed (pawn).
     * @return a boolean on whether a pawn should be promoted to a queen
     */
    public boolean pawnToQueen(int row, String piece) {
        if (row == 0 && piece.charAt(0) == 'P') {
            return true;
        }
        return false;
    }

    /**
     * @param row          index of row location on board
     * @param column       index of column location on board
     * @param currentBoard the current state of the game board
     * @return a boolean on whether a position is an empty tile
     */
    public boolean isEmptyTile(int row, int column, String[][] currentBoard) {
        if (currentBoard[row][column].equals(""))
            return true;
        return false;
    }

    /**
     * @param movingPiece   piece that is being used
     * @param possibleEnemy returns true if is an enemy
     * @return a boolean on whether the piece on a tile is an enemy
     */
    public boolean enemy(String movingPiece, String possibleEnemy) {
        if (movingPiece.length() == 0 || possibleEnemy.length() == 0 ||  movingPiece.charAt(2) == possibleEnemy.charAt(2)) {//needs to check if empty string before trying to get any chars from it
            return false;//not an enemy
        }
        return true;//is an enemy
    }

    /**
     * @param movingPiece  piece that is being used
     * @param possibleAlly returns true if is an ally
     * @return a boolean on whether a piece is an ally, redundant right now should be removed eventually
     */
    public boolean ally(String movingPiece, String possibleAlly) {
        if (movingPiece.isEmpty() || possibleAlly.isEmpty() || movingPiece.charAt(2) != possibleAlly.charAt(2)) {//needs to check if empty string before trying to get any chars from it
            return false; // not a friendly
        }
        return true;//is an ally
    }

    /**
     * @param row    index of row location on board
     * @param column index of column location on board
     * @param board  representation of the game board
     * @return a boolean on whether a row and column is a valid position on the board
     */
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

    /**
     * @param row    index of row location on board
     * @param column index of column location on board
     * @return a two element array for a given row and column
     */
    public int[] position(int row, int column) {
        int[] pos = {row, column};
        return pos;
    }

    /**
     * @param row          , row of selected position
     * @param column       , column of selected position
     * @param currentBoard , takes current board
     * @return returns an ArrayList of the possible moves for a pawn at a given location on the chess board.
     * Includes possible enemies to take, and excludes allied occupied squares. Two forward squares are available for the pawns first move.
     */
    public ArrayList<int[]> pawnPossibleMoves(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> PAWNpossibleMoves = new ArrayList<>();
        //TODO en passant later
        boolean directlyInFront = false;
        if (validPosition(row - 1, column, currentBoard)) {//position directly in front is empty and therefore pawn can move
            if (currentBoard[row - 1][column].equals("")) {
                if (!ally(currentBoard[row][column], currentBoard[row - 1][column])) { //Checks if is not an ally,therefore either an enemy or empty square
                    PAWNpossibleMoves.add(position(row - 1, column));
                    directlyInFront = true;
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
        if (Integer.parseInt(currentBoard[row][column].substring(3)) == 0) {
            if (validPosition(row - 2, column, currentBoard)) {
                if (directlyInFront) {
                    if (isEmptyTile(row - 2, column, currentBoard)) {
                        PAWNpossibleMoves.add(position(row - 2, column));
                    }
                }
            }
        }
        return PAWNpossibleMoves;
    }

    /**
     * @param row          , row of selected position
     * @param column       , column of selected position
     * @param currentBoard , takes current board
     * @return returns an ArrayList of the possible moves for a Bishop at a given location on the chess board for the down/right diagonal.
     * Includes possible enemies to take, and excludes allied occupied squares.
     */
    public ArrayList<int[]> getPossibleDiagonalMoves(int row, int column, String[][] currentBoard){
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        System.out.println("what the heck is going on");
        boolean condition = true;

        boolean downRight = true;
        boolean upRight = true;
        boolean downLeft = true;
        boolean upLeft = true;
        int i = 1;

        while (condition) {

            if(downRight){
                if (validPosition(row + i, column + i, currentBoard)) {
                    if (isEmptyTile(row + i, column + i, currentBoard) || enemy(currentBoard[row][column], currentBoard[row + i][column + i])) {
                        possibleMoves.add(position(row + i, column + i));
                        if (enemy(currentBoard[row][column], currentBoard[row + i][column + i])) { //if an enemy is found, add possible move and then break the loop
                            downRight = false;
                        }
                    } else { //if tile is not empty and is not caught by enemy boolean, it is a friendly and the move is not added
                        downRight = false;
                    }
                } else { //if the move is not a valid position, the loop is broken
                    downRight = false;
                }
            }

            if (upRight){
                if (validPosition(row - i, column + i, currentBoard)) {
                    if (isEmptyTile(row - i, column + i, currentBoard) || enemy(currentBoard[row][column], currentBoard[row - i][column + i])) {
                        possibleMoves.add(position(row - i, column + i));
                        if (enemy(currentBoard[row][column], currentBoard[row - i][column + i])) {//if an enemy is found, add possible move and then break the loop
                            upRight = false;
                        }
                    } else {//if tile is not empty and is not caught by enemy boolean, it is a friendly and the move is not added
                        upRight = false;
                    }
                } else {//if the move is not a valid position, the loop is broken
                    upRight = false;
                }
            }

            if (downLeft){
                if (validPosition(row + i, column - i, currentBoard)) {
                    if (isEmptyTile(row + i, column - i, currentBoard) || enemy(currentBoard[row][column], currentBoard[row + i][column - i])) {
                        possibleMoves.add(position(row + i, column - i));
                        if (enemy(currentBoard[row][column], currentBoard[row + i][column - i])) {//if an enemy is found, add possible move and then break the loop
                            downLeft = false;
                        }
                    } else {//if tile is not empty and is not caught by enemy boolean, it is a friendly and the move is not added
                        downLeft = false;
                    }
                } else {//if the move is not a valid position, the loop is broken
                    downLeft = false;
                }
            }

            if(upLeft){
                if (validPosition(row - i, column - i, currentBoard)) {
                    if (isEmptyTile(row - i, column - i, currentBoard) || enemy(currentBoard[row][column], currentBoard[row - i][column - i])) {
                        possibleMoves.add(position(row - i, column - i));
                        if (enemy(currentBoard[row][column], currentBoard[row - i][column - i])) {//if an enemy is found, add possible move and then break the loop
                            upLeft = false;
                        }
                    } else {//if tile is not empty and is not caught by enemy boolean, it is a friendly and the move is not added
                        upLeft = false;
                    }
                } else {//if the move is not a valid position, the loop is broken
                    upLeft = false;
                }
            }

            if(!downRight){
                if(!downLeft){
                    if(!upRight){
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




    public ArrayList<int[]> possibleHorizontalAndVerticalMoves(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> horizontalnadVerticalMoves = new ArrayList<>();

        for (int i = row - 1; i >= 0; i--) { //up
            if (ally(currentBoard[row][column], currentBoard[i][column])) {//if there is an ally at the board index, loop breaks and possible move location is not added
                break;
            }
            if (enemy(currentBoard[row][column], currentBoard[i][column])) {//if there is an enemy at the board index, the move is added and then the loop is broken
                horizontalnadVerticalMoves.add(position(i, column));
                break;
            }
            if (validPosition(i, column, currentBoard)) {//if both other checks are passed, move is added as possible if at a valid index of the board.
                horizontalnadVerticalMoves.add(position(i, column));
            }
        }

        for (int i = column - 1; i > 0; i--) { // left
            if (ally(currentBoard[row][column], currentBoard[row][i])) {//if there is an ally at the board index, loop breaks and possible move location is not added
                break;
            }
            if (enemy(currentBoard[row][column], currentBoard[row][i])) {//if there is an enemy at the board index, the move is added and then the loop is broken
                horizontalnadVerticalMoves.add(position(row, i));
                break;
            }
            if (validPosition(row, i, currentBoard)) {//if both other checks are passed, move is added as possible if at a valid index of the board.
                horizontalnadVerticalMoves.add(position(row, i));
            }
        }

        for (int i = row + 1; i < currentBoard.length; i++) { //down
            if (ally(currentBoard[row][column], currentBoard[i][column])) {//if there is an ally at the board index, loop breaks and possible move location is not added
                break;
            }
            if (enemy(currentBoard[row][column], currentBoard[i][column])) {//if there is an enemy at the board index, the move is added and then the loop is broken
                horizontalnadVerticalMoves.add(position(i, column));
                break;
            }
            if (validPosition(i, column, currentBoard)) {//if both other checks are passed, move is added as possible if at a valid index of the board.
                horizontalnadVerticalMoves.add(position(i, column));
            }
        }

        for (int i = column + 1; i < currentBoard[0].length; i++) { // right
            if (ally(currentBoard[row][column], currentBoard[row][i])) {//if there is an ally at the board index, loop breaks and possible move location is not added
                break;
            }
            if (enemy(currentBoard[row][column], currentBoard[row][i])) {//if there is an enemy at the board index, the move is added and then the loop is broken
                horizontalnadVerticalMoves.add(position(row, i));
                break;
            }
            if (validPosition(row, i, currentBoard)) {//if both other checks are passed, move is added as possible if at a valid index of the board.
                horizontalnadVerticalMoves.add(position(row, i));
            }
        }

        return horizontalnadVerticalMoves;
    }

    /**
     * @param row          , row of selected position
     * @param column       , column of selected position
     * @param currentBoard , takes current board
     * @return returns an ArrayList of the possible moves for the King at a given location on the chess board for the possible moves that are visible to the King.
     * Includes possible enemies to take, and excludes allied occupied squares.
     * These moves are hardcoded, since there is only a maximum number of 8 possible moves that a king could potentially make.
     */
    public ArrayList<int[]> kingPossibleMoves(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> KINGpossibleMoves = new ArrayList<>();

        //moves are checked for each square surrounding the King
        if (validPosition(row - 1, column - 1, currentBoard)) { //checks if valid position
            if (!ally(currentBoard[row][column], currentBoard[row - 1][column - 1])) {//Checks if is not an ally,therefore either an enemy or empty square
                KINGpossibleMoves.add(position(row - 1, column - 1)); //adds move to ArrayList
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
     * @param row          , row of selected position
     * @param column       , column of selected position
     * @param currentBoard , takes current board
     * @return returns an ArrayList of the possible moves for a Knight at a given location on the chess board for possible moves that are visible to the Knight.
     * Includes possible enemies to take, and excludes allied occupied squares.
     * These moves are hardcoded, since there is only a maximum number of 8 possible moves that a Knight could potentially make.
     */
    public ArrayList<int[]> horsePossibleMoves(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> HORSEpossibleMoves = new ArrayList<>();

        if (validPosition(row - 2, column - 1, currentBoard)) {//checks if valid position
            if (!ally(currentBoard[row][column], currentBoard[row - 2][column - 1])) {//Checks if is not an ally,therefore either an enemy or empty square
                HORSEpossibleMoves.add(position(row - 2, column - 1));// adds move to ArrayList
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

    /**
     * @param board , 2D array of the gameboard
     * @return returns the flipped board so that the game is playable from both white and black. The board flips after each move that is made.
     * A flip does not alter row and column locations.
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
}