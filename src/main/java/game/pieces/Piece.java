package game.pieces;

import java.util.ArrayList;

import static game.Main.board1;

public abstract class Piece {
    public int[] pos;
    public char color;
    public boolean moved;
    public int[][] moveArray;

    public Piece(int[] pos, char color) {
        this.pos = pos;
        this.color = color;
        this.moved = false;
    }

    public abstract void move(int[] newPosition);

    public abstract void capture();

    public int[][] getMoveArray() {
        return this.moveArray;
    }

    public int[][] getValidMoves(int[][] moveArray) {
        int[] proposedMove;
        ArrayList<int[]> validMoves = new ArrayList<>();
        int max;
        if (this.getClass().getSimpleName().equals("King")
                || this.getClass().getSimpleName().equals("Knight")
                || this.getClass().getSimpleName().equals("Pawn"))
            max = 2;
        else max = 7;

        for (int[] ints : moveArray) {
            secondLoop:
            for (int j = 1; j < max; j++) {
                proposedMove = addition(this.pos, multiply(ints, j));

                //Check if move is out of bounds
                if (
                    proposedMove[0] < 8 &&
                    proposedMove[1] < 8 &&
                    proposedMove[0] >= 0 &&
                    proposedMove[1] >= 0
                ) {
                    //Check if there's a piece in the way
                    if (board1.board[proposedMove[0]][proposedMove[1]] == null) {
                        validMoves.add(proposedMove);
                    } else {
                        //Check if it's a capture
                        if (board1.board[proposedMove[0]][proposedMove[1]].color != this.color) {
                            validMoves.add(proposedMove);
                        }
                        break secondLoop;
                    }
                } else break secondLoop;

            }
        }
        return validMoves.toArray(new int[0][]);
    }



    public abstract boolean checkIfValidMove(int[] newPosition);

    public void isCaptured() {
        System.out.println(this.getClass().getSimpleName() + " is captured");
    }

    public int[] multiply(int[] matrix, int factor) {
        int[] newMatrix = matrix.clone();
        for (int i = 0; i < 2; i++) {
            newMatrix[i] *= factor;
        }
        return newMatrix;
    }

    public int[] addition(int[] matrix1, int[] matrix2) {
        int[] newMatrix1 = matrix1.clone();
        int[] newMatrix2 = matrix2.clone();
        for (int i = 0; i < 2; i++) {
            newMatrix1[i] += newMatrix2[i];
        }
        return newMatrix1;
    }
}
