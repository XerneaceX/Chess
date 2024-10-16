package game;

import game.pieces.*;
import game.players.Player;

import java.util.ArrayList;

public class Board {
    public Piece[][] board;
    public ArrayList<Piece> pieces;
    public static Player white, black;

    public Board() {
        this.board = new Piece[8][8];
        white = new Player('w');
        black = new Player('b');
    }

    public void setPieces() {
        //set all the pieces on the board
        this.board[1][1] = new Bishop(new int[]{1, 1}, 'w');
        this.board[3][6] = new King(new int[]{3, 6}, 'w');

        this.board[4][4] = new King(new int[]{4, 4}, 'b');
        this.board[2][2] = new Bishop(new int[]{2, 2}, 'b');


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
        System.out.println("-------------------------------------");
    }

    public void movePiece(int[] oldPosition, int[] newPosition) {
        this.board[oldPosition[0]][oldPosition[1]].move(newPosition, false);
        printBoard();
    }



}
