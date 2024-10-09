package game;

import game.pieces.*;
import game.players.Player;

import java.util.ArrayList;

public class Board {
    public Piece[][] board;
    public ArrayList<Piece> pieces;
    private Player white, black;

    public Board() {
        this.board = new Piece[8][8];
        white = new Player('w');
        black = new Player('b');
    }

    public void setPieces() {
        //set all the pieces on the board
        this.board[1][1] = new Bishop(new int[]{1, 1}, 'w');
        this.board[2][2] = new King(new int[]{2, 2}, 'b');
        this.board[7][6] = new King(new int[]{7, 6}, 'w');
    }

    public void printBoard() {
        System.out.println("-------------------------------------");
        for (int x = 0; x < this.board[0].length; x++) {
            for (int y = 0; y < this.board[x].length; y++) {
                if (this.board[x][y] != null) {
                    System.out.print(" " + this.board[x][y].getClass().getSimpleName() + " ");
                } else System.out.print(" none ");
            }
            System.out.println("\n");
        }
        System.out.println("--------------------------------------");
    }

    public void movePiece(int[] oldPosition, int[] newPosition) {
        this.board[oldPosition[1]][oldPosition[1]].move(newPosition);

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

        System.out.println(black.kingIsInCheck());
    }


}
