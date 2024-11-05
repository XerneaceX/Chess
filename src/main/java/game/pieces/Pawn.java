package game.pieces;

import java.util.ArrayList;

import static game.Main.board1;

public class Pawn extends Piece {
    public Pawn(int[] pos, char color) {
        super(pos, color);
        this.justPushedTwo = false;

        switch (color) {
            case 'w' -> {
                this.moveArray = new int[][]{
                        {0, 1},
                };
            }
            case 'b' -> {
                this.moveArray = new int[][]{
                        {0, -1},
                };
            }
        }
    }

    @Override
    public void move(int[] newPosition, boolean simulated) {
        ArrayList<int[]> moveArray = new ArrayList<>();

        //check if en passant is out of bounds, then check if there's a pawn on the side of the attacking piece. For each of them, check if they're the opposite color and justPushed
        if ((this.pos[0] - 1 >= 0 && this.pos[1] - 1 >= 0) && (board1.board[this.pos[0] - 1][this.pos[1]] != null && board1.board[this.pos[0] - 1][this.pos[1]].justPushedTwo) || (board1.board[this.pos[0] + 1][this.pos[1]] != null && board1.board[this.pos[0] + 1][this.pos[1]].justPushedTwo)) {
            switch (color) {
                case 'w' -> {
                    moveArray.add(new int[]{0, 1});
                    moveArray.add(new int[]{newPosition[0] - this.pos[0], 1});
                    moveArray.add(new int[]{newPosition[0] - this.pos[0], 0});
                }
                case 'b' -> {
                    moveArray.add(new int[]{0, -1});
                    moveArray.add(new int[]{newPosition[0] - this.pos[0], -1});
                    moveArray.add(new int[]{newPosition[0] - this.pos[0], 0});
                }
            }
            this.moveArray = moveArray.toArray(new int[moveArray.size()][]);
            super.move(new int[]{newPosition[0], this.pos[1]}, simulated);
        }
        if ((this.pos[0] - 1 >= 0 && this.pos[1] - 1 >= 0) && (board1.board[newPosition[0]][newPosition[1]] != null && board1.board[newPosition[0]][newPosition[1]].getClass().getSimpleName().equals("Pawn") && board1.board[newPosition[0]][newPosition[1]].color != color)) {
            switch (color) {
                case 'w' -> {
                    moveArray.add(new int[]{0, 1});
                    moveArray.add(new int[]{newPosition[0] - this.pos[0], 1});
                }
                case 'b' -> {
                    moveArray.add(new int[]{0, -1});
                    moveArray.add(new int[]{newPosition[0] - this.pos[0], -1});
                }
            }
            this.moveArray = moveArray.toArray(new int[moveArray.size()][]);
        }
        super.move(newPosition, simulated);
        if (!simulated) switch (color) {
            case 'w' -> {
                this.moveArray = new int[][]{
                        {0, 1},
                };
            }
            case 'b' -> {
                this.moveArray = new int[][]{
                        {0, -1},
                };
            }
        }
        if (this.pos[1] == 7 || this.pos[1] == 0) {
            promote();
        }
    }
    private void promote(){
        board1.board[this.pos[0]][this.pos[1]] = null;
        board1.createPiece("Queen", this.color, this.pos);
    }

}