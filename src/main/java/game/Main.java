package game;

import game.players.Player;

public class Main {
    public static void main(String[] args) {
        Game game1 = new Game();
        game1.setPieces();
        game1.printBoard();
//        board1.movePiece(new int[]{0,6}, new int[]{0,7});
//        board1.movePiece(new int[]{0,7}, new int[]{7,0});
    }
}
