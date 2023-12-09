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
        String[][] starterBoard = {// R = rook, H = horse(knight), B = Bishop, Q = queen, K = king, P = pawn
                {"R_B", "H_B", "B_B", "Q_B", "K_B", "B_B", "H_B","R_B"},
                {"P_B", "P_B", "P_B", "P_B", "P_B", "P_B", "P_B","P_B"},
                {""   , ""   , ""   , ""   , ""   , ""   , ""   , ""  },
                {""   , ""   , ""   , ""   , ""   , ""   , ""   , ""  },
                {""   , ""   , ""   , ""   , ""   , ""   , ""   , ""  },
                {""   , ""   , ""   , ""   , ""   , ""   , ""   , ""  },
                {"P_W", "P_W", "P_W", "P_W", "P_W", "P_W", "P_W","P_W"},
                {"R_W", "H_W", "B_W", "Q_W", "K_W", "B_B", "H_B","R_B"},
        };
        new BoardGui(starterBoard);
    }
}