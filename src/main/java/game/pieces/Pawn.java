package game.pieces;

import game.Game;
import game.players.Player;

import java.util.ArrayList;

public class Pawn extends Piece {


    public Pawn(int[] pos, Player player, Game game) {
        super(pos, player, game);
        justPushedTwo = false;
    }

    @Override
    protected void defineMoveArray() {
        switch (getColor()) {
            case 'w' -> {
                setMoveArray(new int[][]{
                        {0, -1},
                });
            }
            case 'b' -> {
                setMoveArray(new int[][]{
                        {0, 1},
                });
            }
        }
    }

    @Override
    public void move(int[] newPosition, boolean simulated) {
        ArrayList<int[]> moveArray = new ArrayList<>();

        //check if en passant is out of bounds, then check if there's a pawn on the side of the attacking piece. For each of them, check if they're the opposite color and justPushed
        if ((getPos()[0] - 1 >= 0 && getPos()[1] - 1 >= 0) && (getGame().board[getPos()[0] - 1][getPos()[1]] != null && getGame().board[getPos()[0] - 1][getPos()[1]].justPushedTwo) || (getGame().board[getPos()[0] + 1][getPos()[1]] != null && getGame().board[getPos()[0] + 1][getPos()[1]].justPushedTwo)) {
            switch (getColor()) {
                case 'w' -> {
                    moveArray.add(new int[]{0, 1});
                    moveArray.add(new int[]{newPosition[0] - getPos()[0], 1});
                    moveArray.add(new int[]{newPosition[0] - getPos()[0], 0});
                }
                case 'b' -> {
                    moveArray.add(new int[]{0, -1});
                    moveArray.add(new int[]{newPosition[0] - getPos()[0], -1});
                    moveArray.add(new int[]{newPosition[0] - getPos()[0], 0});
                }
            }
            setMoveArray(moveArray.toArray(new int[moveArray.size()][]));
            super.move(new int[]{newPosition[0], getPos()[1]}, simulated);
        }
        if ((getPos()[0] - 1 >= 0 && getPos()[1] - 1 >= 0) && (getGame().board[newPosition[0]][newPosition[1]] != null && getGame().board[newPosition[0]][newPosition[1]].getClass().getSimpleName().equals("Pawn") && getGame().board[newPosition[0]][newPosition[1]].getColor() != getColor())) {
            switch (getColor()) {
                case 'w' -> {
                    moveArray.add(new int[]{0, 1});
                    moveArray.add(new int[]{newPosition[0] - getPos()[0], 1});
                }
                case 'b' -> {
                    moveArray.add(new int[]{0, -1});
                    moveArray.add(new int[]{newPosition[0] - getPos()[0], -1});
                }
            }
            setMoveArray(moveArray.toArray(new int[moveArray.size()][]));
        }
        super.move(newPosition, simulated);
        if (!simulated) defineMoveArray();
        if (getPos()[1] == 7 || getPos()[1] == 0) {
            promote();
        }
    }

    private void promote() {
        getGame().board[getPos()[0]][getPos()[1]] = null;
        getGame().addPiece("Queen", getBelongsTo(), getPos());
    }

}