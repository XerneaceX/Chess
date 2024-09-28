package game.pieces;

public class Knight extends Piece {
    public Knight(int[] pos, char color) {
        super(pos, color);
    }

    @Override
    public void move(int[] newPosition) {

    }

    @Override
    public void capture() {

    }

    @Override
    protected boolean checkIfValidMove(int[] newPosition) {
        return false;
    }
}
