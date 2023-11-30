public class GameBoard {

    private Piece[][]board = new Piece[8][8];

    public GameBoard(){

    }

    public String GameBoard(){
        return "";
    }
    public void MovePiece(){

    }
    public void RemovePiece(){

    }


    public Piece getSpace(int row, int col){

        return board[row][col];
    }
}
