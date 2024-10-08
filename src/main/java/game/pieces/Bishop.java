package game.pieces;

import java.util.ArrayList;
import java.util.Arrays;

import static game.Main.board1;

public class Bishop extends Piece {
    int[][] moveArray;

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
    public void move(int[] newPosition) {
        if (checkIfValidMove(newPosition)) {
            this.pos = newPosition;
        }
    }

    @Override
    public void capture() {
    }

    @Override
    public int[][] getValidMoves() {

        int[] proposedMove;
        ArrayList<int[]> validMoves = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                secondLoop:
                for (int j = 1; j < 7; j++) {
                    proposedMove = addition(new int[]{1,1}, multiply(this.moveArray[i], j));

                    //Check if move is out of bounds
                    if (
                            proposedMove[0] < 8 &&
                            proposedMove[1] < 8 &&
                            proposedMove[0] >= 0 &&
                            proposedMove[1] >= 0
                    ) {
                        //Check if there's a piece in the way
                        if (board1.board[proposedMove[0]][proposedMove[1]] == null) {
                            System.out.println("new move: " + proposedMove[0] + " " + proposedMove[1]);
                            validMoves.add(proposedMove);
                        } else {
                            //Check if it's a capture
                            if (board1.board[proposedMove[0]][proposedMove[1]].color != this.color){
                                System.out.println("new move: " + proposedMove[0] + " " + proposedMove[1]);
                                validMoves.add(proposedMove);
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
        for (int[] validMove : getValidMoves()) {
            if (newPosition[0] == validMove[0] && newPosition[1] == validMove[1]) {
                System.out.println("VALID MOVE!");
                return true;
            }
        }
        //if not a valid move;
        return false;
    }
}
