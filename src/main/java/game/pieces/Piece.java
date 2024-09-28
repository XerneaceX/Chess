package game.pieces;

public abstract class Piece {
    public int[] pos;
    public char color;
    public boolean moved;

    public Piece(int[] pos, char color) {
        this.pos = pos;
        this.color = color;
        this.moved = false;
    }
    public abstract void move(int[] newPosition);
    public abstract void capture();

    protected abstract boolean checkIfValidMove(int[] newPosition);

    public void isCaptured(){
        System.out.println(this.getClass().getSimpleName() + " is captured");
    }
}
