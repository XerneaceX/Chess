package game.pieces;

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
        //check if en passant is out of bounds, then check if there's a pawn on the side of the attacking piece. For each of them, check if they're the opposite color and justPushed
        if ((this.pos[0] - 1 >= 0 && this.pos[1] - 1 >= 0) && (board1.board[this.pos[0] - 1][this.pos[1]] != null && board1.board[this.pos[0] - 1][this.pos[1]].justPushedTwo) || (board1.board[this.pos[0] + 1][this.pos[1]] != null && board1.board[this.pos[0] + 1][this.pos[1]].justPushedTwo )) {
            System.out.println("aaaa");
            switch (color) {
                case 'w' -> {
                    this.moveArray = new int[][]{
                            {0, 1},
                            {newPosition[0]-this.pos[0], 1},
                            {newPosition[0]-this.pos[0], 0},
                    };
                }
                case 'b' -> {
                    this.moveArray = new int[][]{
                            {0, -1},
                            {newPosition[0]-this.pos[0], -1},
                            {newPosition[0]-this.pos[0], 0},
                    };
                }
            }
            super.move(new int[]{newPosition[0], this.pos[1]}, simulated);
        }
        super.move(newPosition, simulated);


    }

    @Override
    public void capture() {
    }

}