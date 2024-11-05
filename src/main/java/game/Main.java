package game;

public class Main {
    public static Board board1;
    public static void main(String[] args) {
        board1 = new Board();
        board1.setPieces();
        board1.printBoard();
        board1.movePiece(new int[]{0,6}, new int[]{0,7});
        board1.movePiece(new int[]{0,7}, new int[]{7,0});
    }
}
