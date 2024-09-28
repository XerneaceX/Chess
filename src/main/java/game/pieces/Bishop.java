package game.pieces;

public class Bishop extends Piece {

    public Bishop(int[] pos, char color) {
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

    public boolean checkIfValidMove(int[] newPosition) {
        int[] oldPosition = this.pos;
        //check if valid move for bishop (if the move is in diagonal). We add +1 when dividing to avoid dividing by 0
        if (Math.abs(oldPosition[0] - newPosition[0]) == Math.abs(oldPosition[1] - newPosition[1])) {
            //returns true if this is a valid move
            return newPosition[0] <= 7 && newPosition[1] <= 7 && newPosition[0] >= 0 && newPosition[1] >= 0;
        }
        //if no valid moves;
        System.out.println("Invalid move!");
        return false;
    }
}
