package game.players;

import java.util.ArrayList;
import java.util.Arrays;

import static game.Main.board1;

public class Player {
    public char color;
    public int[] kingPos;
    public Player(char color) {
        this.color = color;
    }

    public void getKingPos() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board1.board[x][y] != null && board1.board[x][y].getClass().getSimpleName().equals("King") && board1.board[x][y].color == this.color) {
                    this.kingPos = new int[]{x, y};
                }
            }
        }
    }

    public ArrayList<int[][]> getEnemyControlledSquares() // I know
    {
        ArrayList<int[][]> controlledSquares = new ArrayList<>();
        //Make a list of all controlled squares
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board1.board[x][y] != null && board1.board[x][y].color != this.color) {
                    System.out.println(board1.board[x][y].getClass().getSimpleName() + " at " + x + ", " + y);
                    controlledSquares
                            .add(board1.board[x][y]
                            .getValidMoves(board1.board[x][y]
                            .getMoveArray()));
                    System.out.println(Arrays.deepToString(board1.board[x][y].getValidMoves(board1.board[x][y].getMoveArray())));;
                }
            }
        }
        return controlledSquares;
    }

    public boolean kingIsInCheck(){
        getKingPos();
        System.out.print("King pos: ");
        System.out.println(Arrays.toString(kingPos));

        for (int[][] moves : getEnemyControlledSquares()) {
            if (moves != null) {
                for (int[] move : moves) {
                    if (move != null) {
                        if (move[0] == this.kingPos[0] && move[1] == this.kingPos[1]) {
                            return true;
                        }
                    }

                }
            }

        }
        return false;
    }
}
