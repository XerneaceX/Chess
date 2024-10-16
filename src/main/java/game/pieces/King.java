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
    public void capture() {

    }

}
