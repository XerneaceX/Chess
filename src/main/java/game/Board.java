package game;

import game.pieces.*;

public class Board {
    public Piece[][] board;

    public Board() {
        this.board = new Piece[8][8];
    }

    public void setPieces() {
        //set all the pieces on the board
        this.board[0][0] = new Bishop(new int[]{0,0},'w');
        this.board[1][1] = new Knight(new int[]{1,1},'b');
    }

    public void printBoard() {
        for (int x = 0; x < this.board[0].length ; x++) {
            for (int y = 0; y < this.board[x].length; y++) {
                if (this.board[x][y] != null) {
                    System.out.print(" " + this.board[x][y].getClass().getSimpleName() + " ");
                } else System.out.print(" none ");
            }
            System.out.println("\n");
        }
    }

    public void movePiece(int[] oldPosition, int[] newPosition) {
        System.out.println("------------------------------");
        this.board[oldPosition[0]][oldPosition[1]].move(newPosition);

        //checks if the piece was moved. If it was; move it on board. If not;
        if (this.board[oldPosition[0]][oldPosition[1]].pos == newPosition) {
            //captures the piece if there's one
            if (this.board[newPosition[0]][newPosition[1]] != null && this.board[newPosition[0]][newPosition[1]].color != this.board[oldPosition[0]][oldPosition[1]].color) {
                this.board[newPosition[0]][newPosition[1]].isCaptured();
                this.board[newPosition[0]][newPosition[1]] = null;
            }

            //changes the position of the piece on the board array. If a piece is there but wasn't captured, the piece won't move
            if (this.board[newPosition[0]][newPosition[1]] == null) {

                this.board[newPosition[0]][newPosition[1]] = this.board[oldPosition[0]][oldPosition[1]];
                this.board[oldPosition[0]][oldPosition[1]] = null;
                this.board[newPosition[0]][newPosition[1]].moved = true;
            }
        }
        printBoard();
    }
}
