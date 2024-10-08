package game.pieces;

public class Pawn extends Piece {
    public Pawn(int[] pos, char color) {
        super(pos, color);
    }

    @Override
    public void move(int[] newPosition) {

    }

    @Override
    public void capture() {

    }

    @Override
    public int[][] getValidMoves() {
        return new int[0][];
    }

    @Override
    public boolean checkIfValidMove(int[] newPosition) {
        int[] oldPosition = this.pos;
        //check if valid move for bishop (if the move is in diagonal). We add +1 when dividing to avoid dividing by 0
        if (Math.abs(oldPosition[0] - newPosition[0]) == Math.abs(oldPosition[1] - newPosition[1])) {
            //returns true if this is a valid move
            return newPosition[0] <= 7 && newPosition[1] <= 7 && newPosition[0] >= 0 && newPosition[1] >= 0;
        }
        //if not a valid move;
        return false;
    }
}