package game.pieces;

import java.util.Arrays;

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
    public abstract int[][] getValidMoves();

    public abstract boolean checkIfValidMove(int[] newPosition);

    public void isCaptured(){
        System.out.println(this.getClass().getSimpleName() + " is captured");
    }

    public int[] multiply(int[] matrix, int factor){
        int[] newMatrix = matrix.clone();
        for (int i = 0; i < 2; i++) {
            newMatrix[i] *= factor;
        }
        return newMatrix;
    }
    public int[] addition(int[] matrix1, int[] matrix2){
        int[] newMatrix1 = matrix1.clone();
        int[] newMatrix2 = matrix2.clone();
        for (int i = 0; i < 2; i++) {
            newMatrix1[i] += newMatrix2[i];
        }
        return newMatrix1;
    }
}
