package game.pieces;

import game.Game;
import game.players.Player;

public class King extends Piece {

    public King(int[] pos, Player player, Game game) {
        super(pos, player, game);
    }

    @Override
    protected void defineMoveArray() {
        setMoveArray(new int[][]{
                {-1, -1},
                {-1, 0},
                {0, -1},
                {1, 0},
                {0, 1},
                {1, 1},
                {-1, 1},
                {1, -1}
        });
    }

    @Override
    public void move(int[] newPosition, boolean simulated) {
        if (!simulated && getPos()[0] - 1 >= 0 && getPos()[0] + 1 <= 7 && getGame().board[newPosition[0] + 1][newPosition[1]].getClass().getSimpleName().equals("Rook") && getGame().board[newPosition[0] + 1][newPosition[1]].getColor() == getColor()) {
            super.move(new int[]{newPosition[0] - 1, newPosition[1]}, false);
            super.move(newPosition, false);
            if (isMoved()) {
                getGame().board[newPosition[0] + 1][newPosition[1]].rock(new int[]{newPosition[0] - 1, newPosition[1]});
            }
        }
        super.move(newPosition, simulated);
        defineMoveArray();
    }

}
