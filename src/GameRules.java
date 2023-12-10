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
                possibleMoves.addAll(bishopPossibleMoves(row, column, currentBoard));
            } else if (piece.charAt(0) == 'Q') {
                possibleMoves.addAll(queenPossibleMoves(row, column, currentBoard));
            } else if (piece.charAt(0) == 'K') {
                possibleMoves.addAll(kingPossibleMoves(row, column, currentBoard));
            }
        }
        return possibleMoves;
    }

    public ArrayList<int[]> pawnPossibleMoves(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> PAWNpossibleMoves = new ArrayList<>();
        //TODO en passant later
        if (validPosition(row - 1, column, currentBoard)) {//position directly infront is empty and therefore pawn can move
            if (currentBoard[row - 1][column].equals("")) {
                PAWNpossibleMoves.add(position(row - 1, column));
            }
        }
        if (validPosition(row - 1, column - 1, currentBoard)) {
            if (enemy(currentBoard[row][column], currentBoard[row - 1][column - 1])) {
                PAWNpossibleMoves.add(position(row - 1, column - 1));
            }

        }
        if (validPosition(row - 1, column + 1, currentBoard)) {
            if (enemy(currentBoard[row][column], currentBoard[row - 1][column + 1])) {
                PAWNpossibleMoves.add(position(row - 1, column + 1));
            }
        }
        return PAWNpossibleMoves;
    }


    public ArrayList<int[]> rookPossibleMoves(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> ROOKpossiblemoves = new ArrayList<>();

        for (int i = currentBoard.length; i >= 0; i--) {
            if (validPosition(i, column, currentBoard)) {
                ROOKpossiblemoves.add(position(i, column));
            }
        }

        for (int j = currentBoard[0].length; j >= 0; j--) {
            if (validPosition(row, j, currentBoard)) {
                ROOKpossiblemoves.add(position(row, j));
            }
        }
        return ROOKpossiblemoves;
    }

    //hardcoded moves for knight since there's only a max number of 8, and it is less dynamic
    public ArrayList<int[]> horsePossibleMoves(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> HORSEpossibleMoves = new ArrayList<>();

        if (validPosition(row - 2, column - 1, currentBoard)) {
            HORSEpossibleMoves.add(position(row - 2, column - 1));
        }
        if (validPosition(row - 2, column + 1, currentBoard)) {
            HORSEpossibleMoves.add(position(row - 2, column + 1));
        }
        if (validPosition(row + 2, column - 1, currentBoard)) {
            HORSEpossibleMoves.add(position(row + 2, column - 1));
        }
        if (validPosition(row - 2, column - 1, currentBoard)) {
            HORSEpossibleMoves.add(position(row + 2, column + 1));
        }
        if (validPosition(row - 1, column - 2, currentBoard)) {
            HORSEpossibleMoves.add(position(row - 1, column - 2));
        }
        if (validPosition(row - 1, column + 2, currentBoard)) {
            HORSEpossibleMoves.add(position(row - 1, column + 2));
        }
        if (validPosition(row + 1, column - 2, currentBoard)) {
            HORSEpossibleMoves.add(position(row + 1, column - 2));
        }
        if (validPosition(row - 1, column - 2, currentBoard)) {
            HORSEpossibleMoves.add(position(row + 1, column + 2));
        }

        return HORSEpossibleMoves;
    }

    public ArrayList<int[]> queenPossibleMoves(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> QUEENpossibleMoves = new ArrayList<>();

        // VERTICAL and HORIZONTAL movement
        for (int i = currentBoard.length; i >= 0; i--) {
            if (validPosition(i, column, currentBoard)) {
                QUEENpossibleMoves.add(position(i, column));
                if (enemy(currentBoard[row][column], currentBoard[i][column])) {
                    QUEENpossibleMoves.add(position(i, column));
                    break;
                }
            }
        }
        for (int j = currentBoard[0].length; j >= 0; j--) {
            if (validPosition(row, j, currentBoard)) {
                QUEENpossibleMoves.add(position(row, j));
                if (enemy(currentBoard[row][column], currentBoard[row][j])) {
                    QUEENpossibleMoves.add(position(row, j));
                    break;
                }
            }
        }

        //DIAGONAL movement
        for (int i = row; i < currentBoard.length; ) {
            for (int j = column; j < currentBoard[0].length; j++) {
                QUEENpossibleMoves.add(position(i, j));
                i++;
            }
        }


        return QUEENpossibleMoves;
    }

    //hardcoded moves for king as well since only max of 8, and less dynamic
    public ArrayList<int[]> kingPossibleMoves(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> KINGpossibleMoves = new ArrayList<>();

        if (validPosition(row - 1, column - 1, currentBoard)) {
            KINGpossibleMoves.add(position(row - 1, column - 1));

        }
        if (validPosition(row, column - 1, currentBoard)) {
            KINGpossibleMoves.add(position(row, column - 1));
        }
        if (validPosition(row + 1, column - 1, currentBoard)) {
            KINGpossibleMoves.add(position(row + 1, column - 1));

        }
        if (validPosition(row - 1, column, currentBoard)) {
            KINGpossibleMoves.add(position(row - 1, column));

        }
        if (validPosition(row + 1, column, currentBoard)) {
            KINGpossibleMoves.add(position(row + 1, column));

        }
        if (validPosition(row - 1, column + 1, currentBoard)) {
            KINGpossibleMoves.add(position(row - 1, column + 1));

        }
        if (validPosition(row, column + 1, currentBoard)) {
            KINGpossibleMoves.add(position(row, column + 1));
        }
        if (validPosition(row + 1, column + 1, currentBoard)) {
            KINGpossibleMoves.add(position(row + 1, column + 1));

        }

        return KINGpossibleMoves;
    }

    public ArrayList<int[]> bishopPossibleMoves(int row, int column, String[][] currentBoard) {
        ArrayList<int[]> BISHOPpossibleMoves = new ArrayList<>();

        for (int i = row; i < currentBoard.length; ) {
            for (int j = column; j < currentBoard[0].length; j++) {
                BISHOPpossibleMoves.add(position(i, j));
                i++;
            }
        }

       // for (int i = column - row; i < currentBoard.length; i++) {
         //   BISHOPpossibleMoves.add(position(i,i));
        //}

        return BISHOPpossibleMoves;
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


    /*
        //TODO Changed this
        public boolean enemy(String movingPiece, String possibleEnemy){
            if(!possibleEnemy.isEmpty() && (movingPiece.charAt(2) != possibleEnemy.charAt(2))){
                return true;
            }
            return false;
        }

     */
    public boolean enemy(String movingPiece, String possibleEnemy) {
        if (movingPiece.length() == 0 || possibleEnemy.length() == 0 || movingPiece.length() > 0 && possibleEnemy.length() > 0 && movingPiece.charAt(2) == possibleEnemy.charAt(2)) {//needs to check if empty string before trying to get any chars from it
            return false;//not an enemy
        }
        return true;//is an enemy
    }


    public int[] position(int row, int column) {
        int[] pos = {row, column};
        return pos;
    }

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

    public String[][] flipBoard(String[][] board) {//will be useful to simplyfy operations
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
