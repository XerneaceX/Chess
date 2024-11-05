package game.players;

import java.util.ArrayList;

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

    public ArrayList<int[][]> getEnemyControlledSquares() {
        ArrayList<int[][]> controlledSquares = new ArrayList<>();
        //Make a list of all controlled squares
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board1.board[x][y] != null && board1.board[x][y].color != this.color) {
                    controlledSquares.add(board1.board[x][y].getPseudoValidMoves(board1.board[x][y].getMoveArray(), true));
                }
            }
        }
        return controlledSquares;
    }

    public boolean kingIsInCheck() {
        getKingPos();
        for (int[][] moves : getEnemyControlledSquares()) {
            if (moves != null) {
                for (int[] move : moves) {
                    if (move != null) {
                        if (move[0] == this.kingPos[0] && move[1] == this.kingPos[1]) {
                            System.out.println("Check!");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void checkIfCheckmate() {
        getKingPos();
        final int[] kPos = new int[]{this.kingPos[0], this.kingPos[1]};
        int[][] moves = (board1.board[kPos[0]][kPos[1]]
                .getPseudoValidMoves(new int[][]{{-1, -1}, {-1, 0}, {0, -1}, {1, 0}, {0, 1}, {1, 1}, {-1, 1}, {1, -1}}, true));
        boolean hasValidMoves = true;
        for (int[] move : moves) {
            hasValidMoves = (board1.board[kPos[0]][kPos[1]].checkIfValidMove(move));
            if (hasValidMoves) {
                break;
            }
        }
        if (!hasValidMoves) {
            System.out.println("Checkmate!");
        }
    }
}
