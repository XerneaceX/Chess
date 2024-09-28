package game.pieces;

public abstract class Piece {
    int[] pos;
    char color;

    public Piece(int[] pos, char color) {
        this.pos = pos;
        this.color = color;
    }
    public abstract void move(int[] newPosition);
    public abstract void capture();

    public boolean checkIfValidMove(int[] newPosition) {
        int[] oldPosition = this.pos;
        //check if valid move for bishop
        if (newPosition[0]+newPosition[1] == oldPosition[0]+oldPosition[1] || newPosition[0]/oldPosition[0] == newPosition[1]/oldPosition[1]) {
            //returns true if this is a valid move
            return newPosition[0] <= 7 && newPosition[1] <= 7 && newPosition[0] >= 0 && newPosition[1] >= 0;
        }
        return false;
    }

    public void isCaptured(){

    }
}
