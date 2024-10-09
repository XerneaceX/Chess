package game.pieces;

public class King extends Piece {

    public King(int[] pos, char color) {
        super(pos, color);
        this.moveArray = new int[][]{
                {-1, -1},
                {-1, 0},
                {0, -1},
                {1, 0},
                {0, 1},
                {1, 1},
                {-1, 1},
                {1, -1}
        };
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

    public int[][] getValidMoves() {
        return super.getValidMoves(this.moveArray);
    }

    @Override
    public boolean checkIfValidMove(int[] newPosition) {

        //check if newPosition is in the valid moves
        for (int[] validMove : getValidMoves()) {
            if (newPosition[0] == validMove[0] && newPosition[1] == validMove[1]) {
                System.out.println("VALID MOVE!");
                return true;
            }
        }
        //if not a valid move;
        return false;
    }
}
