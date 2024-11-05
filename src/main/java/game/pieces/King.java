package game.pieces;

import static game.Main.board1;

public class King extends Piece {

    public King(int[] pos, char color) {
        super(pos, color);
        this.moveArray = new int[][]{
                {-1, -1},
                {-1, 0},
                {0, -1},
                {1, 0},
                {0, 1},
                {1, 1},
                {-1, 1},
                {1, -1}
        };
    }

    @Override
    public void move(int[] newPosition, boolean simulated) {
        if (!simulated && this.pos[0] - 1 >= 0 &&this.pos[0] + 1 <= 7&& board1.board[newPosition[0]+1][newPosition[1]].getClass().getSimpleName().equals("Rook") && board1.board[newPosition[0]+1][newPosition[1]].color == this.color) {
            super.move(new int[]{newPosition[0]-1, newPosition[1]} ,false);
            super.move(newPosition, false);
            if (this.moved){
                board1.board[newPosition[0]+1][newPosition[1]].rock(new int[]{newPosition[0]-1, newPosition[1]});
            }
        }
        super.move(newPosition, simulated);
        this.moveArray = new int[][]{
                {-1, -1},
                {-1, 0},
                {0, -1},
                {1, 0},
                {0, 1},
                {1, 1},
                {-1, 1},
                {1, -1}
        };    }

}
