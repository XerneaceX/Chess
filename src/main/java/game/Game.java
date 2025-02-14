package game;

import game.pieces.*;
import game.players.Player;

public class Game {
    public static final int BOARD_SIZE = 8;
    public Piece[][] board;
    public Player white, black;

    public Game() {
        this.board = new Piece[8][8];
        this.white = new Player('w',this );
        this.black = new Player('b', this );
    }

    public void setPieces() {
        //set all the pieces on the board
        addPiece("Pawn", white, new int[]{1,1});
        addPiece("King", black, new int[]{0,0});
        addPiece("King" , white , new int[]{4,0});
    }

    public void addPiece(String pieceType, Player player, int[] pos) {
        switch (pieceType.toLowerCase()) {
            case "bishop" -> this.board[pos[0]][pos[1]] = new Bishop(pos, player, this);
            case "king" -> this.board[pos[0]][pos[1]] = new King(pos, player, this);
            case "knight" -> this.board[pos[0]][pos[1]] = new Knight(pos, player, this);
            case "pawn" -> this.board[pos[0]][pos[1]] = new Pawn(pos, player, this);
            case "queen" -> this.board[pos[0]][pos[1]] = new Queen(pos, player, this);
            case "rook" -> this.board[pos[0]][pos[1]] = new Rook(pos, player, this);
            default -> throw new IllegalArgumentException("Invalid piece type");
        }
    }

    public void printBoard() {
        System.out.println("-------------------------------------");
        for (int Ypos = BOARD_SIZE-1; Ypos >= 0; Ypos--) {
            for (int Xpos = 0; Xpos < BOARD_SIZE; Xpos++) {
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
