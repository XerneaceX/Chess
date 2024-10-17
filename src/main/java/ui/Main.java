package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {
    final int BOARD_SIZE = 8;
    final int SQUARE_SIZE = 50;
    GridPane board = new GridPane();
    MenuBar menuBar = new MenuBar();



    private void populateBoard() throws InterruptedException {
        Rectangle square;
        for (int y = 0; y < BOARD_SIZE ; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                switch (y) {
                    case 0, 2, 4, 6 -> {
                        if (x % 2 == 0) {
                            square = new Rectangle(SQUARE_SIZE, SQUARE_SIZE, Color.LIGHTGRAY);
                        } else {
                            square = new Rectangle(SQUARE_SIZE, SQUARE_SIZE, Color.DARKSEAGREEN);
                        }
                    }
                    case 1, 3, 5, 7 -> {
                        if (x % 2 != 0) {
                            square = new Rectangle(SQUARE_SIZE, SQUARE_SIZE, Color.LIGHTGRAY);
                        } else {
                            square = new Rectangle(SQUARE_SIZE, SQUARE_SIZE, Color.DARKSEAGREEN);
                        }
                    }
                    default -> square = new Rectangle(SQUARE_SIZE, SQUARE_SIZE, Color.RED);
                }
                final int finalX = x;
                final int finalY = BOARD_SIZE-y-1;
                square.setX(SQUARE_SIZE * finalX);
                square.setY(SQUARE_SIZE * finalY);


                final Rectangle finalSquare = square;

                finalSquare.setOnMouseClicked(e -> {
                    System.out.println(finalX + " , " + finalY);
                });


                board.add(finalSquare, x, y);
            }
        }
    }

    private Image createPieces() throws InterruptedException, FileNotFoundException {
        return new Image(new FileInputStream("C:\\Users\\Bouch\\Downloads\\chesspiece_8246039.png"));
    }

    private void populateMenuBar(){
        Menu files = new Menu("File");
        Menu settings = new Menu("Settings");

        files.getItems().add(new MenuItem("Open Position from PGN file (TODO)"));
        files.getItems().get(0).setOnAction(e -> {
            System.out.println("Feature not implemented yet");
        });

        menuBar.getMenus().add(files);
        menuBar.getMenus().add(settings);
    }

    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        stage.setScene(new Scene(root, BOARD_SIZE*SQUARE_SIZE, BOARD_SIZE*SQUARE_SIZE+30, Color.GRAY));
        stage.setTitle("Chess");

        //Create menu
        root.setTop(menuBar);
        populateMenuBar();

        //Create board
        root.setBottom(board);
        populateBoard();


        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
