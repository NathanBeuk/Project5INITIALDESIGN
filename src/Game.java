/**
 * Description
 * @author Adoniram Courser and Nathan Beukema
 * @version 1.0
 * @since 11-29-23
 * Reference: https://stackoverflow.com/questions/284899/how-do-you-add-an-actionlistener-onto-a-jbutton-in-java
 */
public class Game {

    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {
        String[][] starterBoard = {// R = rook, H = horse(knight), B = Bishop, Q = queen, K = king, P = pawn, first column will just be command lines
                {"forward" , "", "", "", "", "", "", "",""},
                {"backward", "", "P_W0", "", "", "", "", "",""},
                {""   , ""    , ""    , ""    , ""    , ""    , ""    , ""    , ""   },
                {""   , ""    , ""    , ""    , ""    , ""    , ""    , ""    , ""   },
                {"",    ""    , ""    , "Q_W0"    , "B_W0"    , ""    , ""    , ""    , ""   },

                {"", ""    , "P_W0"    , "B_B0"    , "B_B0"    , "B_W0"    , ""    , ""    , ""   },
                {"" , "", "", "", "", "", "", "",""},

                {"end", ""    , ""    , "B_B0"    , "B_B0"    , "B_W0"    , ""    , ""    , ""   },

        };
        new Board(starterBoard);



    }
}