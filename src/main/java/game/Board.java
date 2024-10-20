package game;

import game.pieces.*;
import game.players.Player;

public class Board {
    public Piece[][] board;
    public static Player white, black;

    public Board() {
        this.board = new Piece[8][8];
        white = new Player('w');
        black = new Player('b');
    }

    public void setPieces() {
        //set all the pieces on the board
        createPiece("Pawn", 'w', new int[]{2,0});
        createPiece("Queen", 'b', new int[]{0,1});

        this.board[3][6] = new King(new int[]{3, 6}, 'w');
        this.board[4][4] = new King(new int[]{4, 4}, 'b');
    }

    public void createPiece(String pieceType, char color, int[] pos) {
        switch (pieceType.toLowerCase()) {
            case "bishop" -> this.board[pos[0]][pos[1]] = new Bishop(pos, color);
            case "king" -> this.board[pos[0]][pos[1]] = new King(pos, color);
            case "knight" -> this.board[pos[0]][pos[1]] = new Knight(pos, color);
            case "pawn" -> this.board[pos[0]][pos[1]] = new Pawn(pos, color);
            case "queen" -> this.board[pos[0]][pos[1]] = new Queen(pos, color);
            case "rook" -> this.board[pos[0]][pos[1]] = new Rook(pos, color);
            default -> throw new IllegalArgumentException("Invalid piece type");
        }
    }

    public void createPiece(String pieceType, char color, int x, int y) {
        createPiece(pieceType, color, new int[]{x, y});
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
