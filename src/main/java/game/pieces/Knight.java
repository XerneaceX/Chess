package game.pieces;

public class Knight extends Piece {
    public Knight(int[] pos, char color) {
        super(pos, color);
        this.moveArray = new int[][]{
                {1,2},
                {2,1},
                {-1,2},
                {-2,1},
                {-2,-1},
                {-1,-2},
                {1,-2},
                {2,-1},
        };
    }

    @Override
    public void capture() {

    }



}
