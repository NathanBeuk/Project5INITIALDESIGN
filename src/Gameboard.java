public class Gameboard {

    private Piece[][]board = new Piece[8][8];

    public Gameboard(){

    }

    public String GameBoard(){
        return "";
    }

    public int getRows(){
        return 0;
    }
    public int getCols(){
        return 0;
    }

    public Piece getSpace(int row, int col){

        return board[row][col];
    }
}
