package game.pieces;

public class Bishop extends Piece {

    public Bishop(int[] pos, char color) {
        super(pos, color);
        this.moveArray = new int[][]{
                {1,1},
                {1,-1},
                {-1,1},
                {-1,-1},
        };

    }

    @Override
    public void capture() {
    }

}
