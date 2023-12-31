/**
 * This is the class with main to start up the gamwe
 * @author Adoniram Courser and Nathan Beukema
 * @version 1.0
 * @since 12/13/23
 * Reference: https://stackoverflow.com/questions/284899/how-do-you-add-an-actionlistener-onto-a-jbutton-in-java
 */
public class Game {
    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {
        String[][] starterBoard = {// R = rook, H = horse(knight), B = Bishop, Q = queen, K = king, P = pawn, first column will just be command lines
                {"forward" , "R_B0", "H_B0", "B_B0", "Q_B0", "K_B0", "B_B0", "H_B0","R_B0"},
                {"backward", "P_B0", "P_B0", "P_B0", "P_B0", "P_B0", "P_B0", "P_B0","P_B0"},
                {""        , ""    , ""    , ""    , ""    , ""    , ""    , ""    , ""   },
                {""        , ""    , ""    , ""    , ""    , ""    , ""    , ""    , ""   },
                {""        , ""    , ""    , ""    , ""    , ""    , ""    , ""    , ""   },
                {""        , ""    , ""    , ""    , ""    , ""    , ""    , ""    , ""   },
                {""        , "P_W0", "P_W0", "P_W0", "P_W0", "P_W0", "P_W0", "P_W0","P_W0"},
                {"end"     , "R_W0", "H_W0", "B_W0", "Q_W0", "K_W0", "B_W0", "H_W0","R_W0"},
        };
        new Board(starterBoard);
    }
}