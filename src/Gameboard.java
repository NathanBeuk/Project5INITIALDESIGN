public class Gameboard {

    private boolean [][]Spaces;

    public Gameboard(){
        Spaces = new boolean[8][8];
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

    public boolean getSpace(int row, int col){
        return Spaces[row][col];
    }
}
