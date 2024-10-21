package game.pieces;

public class Rook extends Piece {
    public Rook(int[] pos, char color) {
        super(pos, color);
        this.moveArray = new int[][]{
                {0,1},
                {1,0},
                {-1,0},
                {0,-1},
        };
    }


    @Override
    public void capture() {

    }


}
