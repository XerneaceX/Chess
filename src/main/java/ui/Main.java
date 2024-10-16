package ui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static javafx.application.Application.launch;

public class Main {
    private static final int BOARD_SIZE = 8;
    private ArrayList<Rectangle> squares = new ArrayList<>();
    private GridPane board;

    private Parent createBoard() throws InterruptedException {
        Rectangle square;
        int SQUARE_SIZE = 50;
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                switch (y) {
                    case 0, 2, 4, 6 -> {
                        if (x % 2 == 0) {
                            square = new Rectangle(SQUARE_SIZE, SQUARE_SIZE, Color.LIGHTGRAY);
                        } else {
                            square = new Rectangle(SQUARE_SIZE, SQUARE_SIZE, Color.LIGHTPINK);
                        }
                    }
                    case 1, 3, 5, 7 -> {
                        if (x % 2 != 0) {
                            square = new Rectangle(SQUARE_SIZE, SQUARE_SIZE, Color.LIGHTGRAY);
                        } else {
                            square = new Rectangle(SQUARE_SIZE, SQUARE_SIZE, Color.LIGHTPINK);
                        }
                    }
                    default -> square = new Rectangle(SQUARE_SIZE, SQUARE_SIZE, Color.RED);
                }

                square.setX(SQUARE_SIZE * x);
                square.setY(SQUARE_SIZE * y);
//                board.add(square);
            }
        }
        return board;
    }

    private Image createPieces() throws InterruptedException, FileNotFoundException {
        Image image1 = new Image(new FileInputStream("C:\\Users\\Bouch\\Downloads\\chesspiece_8246039.png"));
        return image1;
    }

    public void start(Stage stage) throws Exception {
        createBoard();
        stage.setScene(new Scene(board));
        stage.setTitle("Chess");
    }
    public static void main(String[] args) {
        launch(args);
    }
}
