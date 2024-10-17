package game.pieces;

public class Pawn extends Piece {

    public Pawn(int[] pos, char color) {
        super(pos, color);
        this.pushedTwo = false;
        this.justPushedTwo = false;
        this.enPassant = false;

        switch (color){
            case 'w' -> {
                this.moveArray = new int[][]{
                        {1, 0},
                };
            }
            case 'b' -> {
                this.moveArray = new int[][]{
                        {-1, 0},
                };
            }
        }
    }
    @Override
    public void capture() {
    }

}