package game.pieces;

import java.util.ArrayList;
import java.util.Arrays;

import static game.Board.black;
import static game.Board.white;
import static game.Main.board1;

public abstract class Piece {
    public int[] pos;
    public char color;
    public boolean moved;
    public int[][] moveArray;
    public boolean captured;
    private Piece copyOfCaptured;

    public boolean justPushedTwo;

    public Piece(int[] pos, char color) {
        this.pos = pos;
        this.color = color;
        this.moved = false;
        this.copyOfCaptured = null;
        this.captured = false;
    }

    public void move(int[] newPosition, boolean simulated) {

        if (simulated && board1.board[newPosition[0]][newPosition[1]] != null && board1.board[newPosition[0]][newPosition[1]].color != this.color) {
            this.copyOfCaptured = board1.board[newPosition[0]][newPosition[1]];
        }

        if (simulated || checkIfValidMove(newPosition)) {
            if (board1.board[newPosition[0]][newPosition[1]] != null && board1.board[newPosition[0]][newPosition[1]].color != board1.board[this.pos[0]][this.pos[1]].color) {

                board1.board[newPosition[0]][newPosition[1]].isCaptured(); //capture piece on attacked square
                board1.board[newPosition[0]][newPosition[1]] = board1.board[this.pos[0]][this.pos[1]]; //move piece
                board1.board[this.pos[0]][this.pos[1]] = null; //remove piece from old position
                board1.board[newPosition[0]][newPosition[1]].moved = true;

            } else if (board1.board[newPosition[0]][newPosition[1]] == null) {

                board1.board[newPosition[0]][newPosition[1]] = board1.board[this.pos[0]][this.pos[1]]; //move piece
                board1.board[this.pos[0]][this.pos[1]] = null; //remove piece from old position
                board1.board[newPosition[0]][newPosition[1]].moved = true;

            }
            this.pos = newPosition;
        }
    }

    public void revertMove(int[] newPosition, Piece copyOfCaptured) {
        copyOfCaptured = this.copyOfCaptured;

        if (copyOfCaptured == null) {
            board1.board[newPosition[0]][newPosition[1]] = board1.board[this.pos[0]][this.pos[1]]; //move piece
            board1.board[this.pos[0]][this.pos[1]] = null; //remove piece from old position
            board1.board[newPosition[0]][newPosition[1]].moved = true;
        } else {
            board1.board[newPosition[0]][newPosition[1]] = board1.board[this.pos[0]][this.pos[1]]; //move piece
            board1.board[this.pos[0]][this.pos[1]] = null; //remove piece from old position
            board1.board[newPosition[0]][newPosition[1]].moved = true;

            board1.board[this.pos[0]][this.pos[1]] = this.copyOfCaptured; //restores captured piece
            this.copyOfCaptured = null;
        }
        this.pos = newPosition;
    }

    public void revertMove(int[] newPosition) {
        revertMove(newPosition, null);
    }


    public boolean simulateMove(int[] newPosition) {
        //Keep the oldPos in memory in case it needs to be reverted
        int[] oldPos = this.pos;

        //Start the test
        move(newPosition, true);
        switch (this.color) {
            case 'w' -> {
                if (white.kingIsInCheck()) {
                    revertMove(oldPos, null);
                    return false;
                }
            }
            case 'b' -> {
                if (black.kingIsInCheck()) {
                    revertMove(oldPos, null);
                    return false;
                }
            }
        }
        revertMove(oldPos, null);
        return true;
    }


    public abstract void capture();

    public int[][] getMoveArray() {
        return this.moveArray;
    }

    public int[][] getValidMoves(int[][] moveArray, boolean simulated) {
        int[] proposedMove;
        ArrayList<int[]> validMoves = new ArrayList<>();
        int max;
        if (this.getClass().getSimpleName().equals("King") || this.getClass().getSimpleName().equals("Knight") || (this.getClass().getSimpleName().equals("Pawn") && this.color == 'w' && this.pos[1] != 1) || (this.getClass().getSimpleName().equals("Pawn") && this.color == 'b' && this.pos[1] != 7)) {
            max = 2;
        } else if (this.getClass().getSimpleName().equals("Pawn")) {
            max = 3;
            this.justPushedTwo = true;
        } else {
            max = 7;
        }

        for (int[] ints : moveArray) {
            secondLoop:
            for (int j = 1; j < max; j++) {
                proposedMove = addition(this.pos, multiply(ints, j));

                //Check if move is out of bounds
                if (proposedMove[0] < 8 && proposedMove[1] < 8 && proposedMove[0] >= 0 && proposedMove[1] >= 0) {
                    //Check if there's a piece in the way
                    if (board1.board[proposedMove[0]][proposedMove[1]] == null) {
                        if (simulated) {
                            validMoves.add(proposedMove);
                        } else if (simulateMove(proposedMove)) {
                            validMoves.add(proposedMove);
                        }
                    } else {
                        //Check if it's a capture
                        if (board1.board[proposedMove[0]][proposedMove[1]].color != this.color) {
                            if (simulated) {
                                validMoves.add(proposedMove);
                            } else if (simulateMove(proposedMove)) {
                                validMoves.add(proposedMove);
                            }
                        }
                        break secondLoop;
                    }
                } else break secondLoop;
            }
        }
        return validMoves.toArray(new int[0][]);
    }

    public boolean checkIfValidMove(int[] newPosition) {

        //check if newPosition is in the valid moves
        for (int[] validMove : getValidMoves(this.moveArray, false)) {
            if (newPosition[0] == validMove[0] && newPosition[1] == validMove[1]) {
                System.out.println("VALID MOVE!");
                return true;
            }
        }
        //if not a valid move;
        return false;
    }

    public void isCaptured() {
//        System.out.println(this.getClass().getSimpleName() + " is captured");
        this.captured = true;
    }

    public void isRestored() {
        System.out.println(this.getClass().getSimpleName() + " is restored");
        this.captured = false;
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
