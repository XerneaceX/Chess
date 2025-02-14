package game.pieces;

import game.Game;
import game.players.Player;

public class Knight extends Piece {


    public Knight(int[] pos, Player player, Game game) {
        super(pos, player, game);
    }

    @Override
    protected void defineMoveArray() {
        setMoveArray(new int[][]{
                {1, 2},
                {2, 1},
                {-1, 2},
                {-2, 1},
                {-2, -1},
                {-1, -2},
                {1, -2},
                {2, -1},
        });
    }
}
