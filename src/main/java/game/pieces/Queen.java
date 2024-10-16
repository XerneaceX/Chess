package game.pieces;

public class Queen extends Piece {

    public Queen(int[] pos, char color) {
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
    public void capture() {

    }



}