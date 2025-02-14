package game.pieces;

import game.Game;
import game.players.Player;

import java.util.ArrayList;

public abstract class Piece {
    private int[] pos;
    private char color;
    private boolean moved;
    private int[][] moveArray;
    private boolean captured;
    private Piece copyOfCaptured;
    private Player belongsTo;
    private Player enemy;
    private Game game;

    public boolean justPushedTwo;

    public Piece(int[] pos, Player player, Game game) {
        this.pos = pos;
        this.moved = false;
        this.copyOfCaptured = null;
        this.captured = false;
        this.game = game;
        if (player.getColor() == 'w') {
            this.color = 'w';
        }
        else if (player.getColor() == 'b') {
            this.color = 'b';
        }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public void setMoveArray(int[][] moveArray) {
        this.moveArray = moveArray;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    public Piece getCopyOfCaptured() {
        return copyOfCaptured;
    }

    public void setCopyOfCaptured(Piece copyOfCaptured) {
        this.copyOfCaptured = copyOfCaptured;
    }

    public Player getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(Player belongsTo) {
        this.belongsTo = belongsTo;
    }

    public Player getEnemy() {
        return enemy;
    }

    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }

    protected abstract void defineMoveArray();

    public void move(int[] newPosition, boolean simulated) {

        if (simulated && game.board[newPosition[0]][newPosition[1]] != null && game.board[newPosition[0]][newPosition[1]].color != this.color) {
            this.copyOfCaptured = game.board[newPosition[0]][newPosition[1]];
        }

        if (simulated || checkIfValidMove(newPosition)) {
            if (game.board[newPosition[0]][newPosition[1]] != null && game.board[newPosition[0]][newPosition[1]].color != game.board[this.pos[0]][this.pos[1]].color) {

                game.board[newPosition[0]][newPosition[1]].isCaptured(); //capture piece on attacked square
                game.board[newPosition[0]][newPosition[1]] = game.board[this.pos[0]][this.pos[1]]; //move piece
                game.board[this.pos[0]][this.pos[1]] = null; //remove piece from old position
                game.board[newPosition[0]][newPosition[1]].moved = true;

            } else if (game.board[newPosition[0]][newPosition[1]] == null) {

                game.board[newPosition[0]][newPosition[1]] = game.board[this.pos[0]][this.pos[1]]; //move piece
                game.board[this.pos[0]][this.pos[1]] = null; //remove piece from old position
                game.board[newPosition[0]][newPosition[1]].moved = true;

            }
            this.pos = newPosition;
            if (!simulated) this.moved = true;

        }
    }

    public void revertMove(int[] newPosition, Piece copyOfCaptured) {
        copyOfCaptured = this.copyOfCaptured;

        if (copyOfCaptured == null) {
            game.board[newPosition[0]][newPosition[1]] = game.board[this.pos[0]][this.pos[1]]; //move piece
            game.board[this.pos[0]][this.pos[1]] = null; //remove piece from old position
            game.board[newPosition[0]][newPosition[1]].moved = true;
        } else {
            game.board[newPosition[0]][newPosition[1]] = game.board[this.pos[0]][this.pos[1]]; //move piece
            game.board[this.pos[0]][this.pos[1]] = null; //remove piece from old position
            game.board[newPosition[0]][newPosition[1]].moved = true;

            game.board[this.pos[0]][this.pos[1]] = this.copyOfCaptured; //restores captured piece
            this.copyOfCaptured = null;
        }
        this.pos = newPosition;
        this.moved = false;
    }

    public void revertMove(int[] newPosition) {
        revertMove(newPosition, null);
    }


    public boolean simulateMove(int[] newPosition) {
        //Keep the oldPos in memory in case it needs to be reverted
        int[] oldPos = this.pos;

        //Start the test
        move(newPosition, true);
        switch (this.color) {
            case 'w' -> {
                if (game.white.kingIsInCheck()) {
                    revertMove(oldPos, null);
                    return false;
                }
            }
            case 'b' -> {
                if (game.black.kingIsInCheck()) {
                    revertMove(oldPos, null);
                    return false;
                }
            }
        }
        revertMove(oldPos, null);
        return true;
    }


    public int[][] getMoveArray() {
        return this.moveArray;
    }

    public int[][] getPseudoValidMoves(int[][] moveArray, boolean simulated) {
        int[] proposedMove;
        ArrayList<int[]> validMoves = new ArrayList<>();
        int max;
        if (this.getClass().getSimpleName().equals("King") || this.getClass().getSimpleName().equals("Knight") || (this.getClass().getSimpleName().equals("Pawn") && this.color == 'w' && this.pos[1] != 1) || (this.getClass().getSimpleName().equals("Pawn") && this.color == 'b' && this.pos[1] != 7)) {
            max = 2;
        } else if (this.getClass().getSimpleName().equals("Pawn")) {
            max = 3;
            this.justPushedTwo = true;
        } else {
            max = 8;
        }

        for (int[] ints : moveArray) {
            secondLoop:
            for (int j = 1; j < max; j++) {
                proposedMove = addition(this.pos, multiply(ints, j));

                //Check if move is out of bounds
                if (proposedMove[0] < 8 && proposedMove[1] < 8 && proposedMove[0] >= 0 && proposedMove[1] >= 0) {
                    //Check if there's a piece in the way
                    if (game.board[proposedMove[0]][proposedMove[1]] == null) {
                        if (simulated) {
                            validMoves.add(proposedMove);
                        } else if (simulateMove(proposedMove)) {
                            validMoves.add(proposedMove);
                        }
                    } else {
                        //Check if it's a capture
                        if (game.board[proposedMove[0]][proposedMove[1]].color != this.color) {
                            if (simulated) {
                                validMoves.add(proposedMove);
                            } else if (simulateMove(proposedMove)) {
                                validMoves.add(proposedMove);
                            }
                        }
                        break secondLoop;
                    }
                } else break secondLoop;
            }
        }
        return validMoves.toArray(new int[0][]);
    }

    public boolean checkIfValidMove(int[] newPosition) {

        //check if newPosition is in the valid moves
        for (int[] validMove : getPseudoValidMoves(this.moveArray, false)) {
            if (newPosition[0] == validMove[0] && newPosition[1] == validMove[1]) {
                System.out.println("VALID MOVE!");
                return true;
            }
        }
        //if not a valid move;
        return false;
    }

    public void isCaptured() {
//        System.out.println(this.getClass().getSimpleName() + " is captured");
        this.captured = true;
    }

    public void isRestored() {
        System.out.println(this.getClass().getSimpleName() + " is restored");
        this.captured = false;
    }

    public int[] multiply(int[] matrix, int factor) {
        int[] newMatrix = matrix.clone();
        for (int i = 0; i < 2; i++) {
            newMatrix[i] *= factor;
        }
        return newMatrix;
    }

    public int[] addition(int[] matrix1, int[] matrix2) {
        int[] newMatrix1 = matrix1.clone();
        int[] newMatrix2 = matrix2.clone();
        for (int i = 0; i < 2; i++) {
            newMatrix1[i] += newMatrix2[i];
        }
        return newMatrix1;
    }

    public void rock(int[] newPosition){
        game.board[newPosition[0]][newPosition[1]] = game.board[this.pos[0]][this.pos[1]]; //move piece
        game.board[this.pos[0]][this.pos[1]] = null; //remove piece from old position
        game.board[newPosition[0]][newPosition[1]].moved = true;
    }

}
