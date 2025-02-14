package game.pieces;

import game.Game;
import game.players.Player;

public class Queen extends Piece {


    public Queen(int[] pos, Player player, Game game) {
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
}