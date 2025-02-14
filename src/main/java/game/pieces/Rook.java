package game.pieces;

import game.Game;
import game.players.Player;

public class Rook extends Piece {

    public Rook(int[] pos, Player player, Game game) {
        super(pos, player, game);
    }

    @Override
    protected void defineMoveArray() {
        setMoveArray(new int[][]{
                {0,1},
                {1,0},
                {1,1},
                {0,0}
        });
    }
}
