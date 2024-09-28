module org.example.chess {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.chess to javafx.fxml;
    opens ui to javafx.fxml;
    exports org.example.chess;
    exports ui;
}