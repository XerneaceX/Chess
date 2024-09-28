package game.pieces;

public class King extends Piece {

    public King(int[] pos, char color) {
        super(pos, color);
    }

    @Override
    public void move(int[] newPosition) {
        if (checkIfValidMove(newPosition)) {
            this.pos = newPosition;
        }
    }

    @Override
    public void capture() {

    }

    @Override
    protected boolean checkIfValidMove(int[] newPosition) {
        int[] oldPosition = this.pos;

        if     (Math.abs(newPosition[0] - oldPosition[0]) == 1 && newPosition[1] == oldPosition[1] ||
                Math.abs(newPosition[1] - oldPosition[1]) == 1 && newPosition[0] == oldPosition[0] ||
                Math.abs(newPosition[1] - oldPosition[1]) == 1 && Math.abs(newPosition[0] - oldPosition[0]) == 1) {
            //returns true if this is a valid move
            return newPosition[0] <= 7 && newPosition[1] <= 7 && newPosition[0] >= 0 && newPosition[1] >= 0;
        }
        //if not a valid move;
        System.out.println("Invalid move");
        return false;
    }
}
