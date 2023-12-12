/**
 * Description
 * @author Adoniram Courser and Nathan Beukema
 * @version 1.0
 * @since 11-29-23
 */
public class Game {

    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {
        String[][] starterBoard = {// R = rook, H = horse(knight), B = Bishop, Q = queen, K = king, P = pawn, first column will just be command lines
                {"forward" , "B_W0", "B_W0", "B_W0", "B_W0", "B_W0", "B_W0", "B_W0","B_W0"},
                {"backward", "B_W0", "B_W0", "B_W0", "B_W0", "B_W0", "B_W0", "B_W0","B_W0"},
                {"*****"   , "B_W0"    , "B_W0"    , "B_W0"    , "B_W0"    , "B_W0"    , "B_W0"    , "B_W0"    , "B_W0"   },
                {"*****"   , "B_W0"    , "B_W0"    , "B_W0"    , "B_W0"    , "B_W0"    , "B_W0"    , "B_W0"    , "B_W0"   },
                {"*****",    "B_W0"    , "B_W0"    , "B_W0"    , "B_W0"    , "B_W0"    , "B_W0"    , "B_W0"    , "B_W0"   },
                {"*****",    "B_W0"    , "B_W0"    , "B_W0"    , "B_W0"    , "B_W0"    , "B_W0"    , "B_W0"    , "B_W0"   },
                {"*****"   , "B_W0", "B_W0", "B_W0", "B_W0", "B_W0", "B_W0", "B_W0","B_W0"},
                {"end"     , "B_W0", "B_W0", "B_W0", "B_W0", "B_W0", "B_W0", "B_W0","B_W0"},
        };
        new BoardGui(starterBoard);



    }
}