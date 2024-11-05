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
        createPiece("Rook", 'b', new int[]{6,7});
        createPiece("King", 'b', new int[]{0,0});
        createPiece("King" , 'w' , new int[]{4,0});
        createPiece("Rook", 'w', new int[]{7,0});
        createPiece("Pawn", 'w', new int[]{0,6});
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

    private void createPiece(String pieceType, char color, int x, int y) {
        createPiece(pieceType, color, new int[]{x, y});
    }

    public void printBoard() {
        System.out.println("-------------------------------------");
        for (int Ypos = 7; Ypos >= 0; Ypos--) {
            for (int Xpos = 0; Xpos < 8; Xpos++) {
                if (this.board[Xpos][Ypos] != null) {
                    System.out.print(" " + this.board[Xpos][Ypos].getClass().getSimpleName() + " ");
                } else System.out.print(" none ");
            }
            System.out.println("\n");
        }
        System.out.println("-------------------------------------");
    }

    public void movePiece(int[] oldPosition, int[] newPosition) {
        this.board[oldPosition[0]][oldPosition[1]].move(newPosition, false);
        printBoard();
        white.checkIfCheckmate();
        black.checkIfCheckmate();
    }

    public void movePiece(int oldX, int oldY, int newX, int newY) {
        movePiece(new int[]{oldX, oldY}, new int[]{newX, newY});
    }


}
