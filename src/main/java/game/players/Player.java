package game.players;

import game.Game;

import java.util.ArrayList;

public class Player {
    private char color;
    private int[] kingPos;
    private Game game;

    public Player(char color , Game game) {
        this.color = color;
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public void setKingPos(int[] kingPos) {
        this.kingPos = kingPos;
    }

    public void getKingPos() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (getGame().board[x][y] != null && getGame().board[x][y].getClass().getSimpleName().equals("King") && getGame().board[x][y].getColor() == this.color) {
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
                if (getGame().board[x][y] != null && getGame().board[x][y].getColor() != this.color) {
                    controlledSquares.add(getGame().board[x][y].getPseudoValidMoves(getGame().board[x][y].getMoveArray(), true));
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
        int[][] moves = (getGame().board[kPos[0]][kPos[1]]
                .getPseudoValidMoves(new int[][]{{-1, -1}, {-1, 0}, {0, -1}, {1, 0}, {0, 1}, {1, 1}, {-1, 1}, {1, -1}}, true));
        boolean hasValidMoves = true;
        for (int[] move : moves) {
            hasValidMoves = (getGame().board[kPos[0]][kPos[1]].checkIfValidMove(move));
            if (hasValidMoves) {
                break;
            }
        }
        if (!hasValidMoves) {
            System.out.println("Checkmate!");
        }
    }
}
