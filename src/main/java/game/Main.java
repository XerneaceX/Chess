package game;

public class Main {
    public static Board board1;
    public static void main(String[] args) {
        board1 = new Board();
        board1.setPieces();
        board1.printBoard();

        board1.movePiece(new int[]{0,0}, new int[]{2,0});

    }
}
