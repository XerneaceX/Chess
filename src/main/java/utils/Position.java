package utils;

import javafx.util.Pair;

import java.awt.*;

public class Position {
    Pair<Integer, Integer> position;

    public Position(int x, int y) {
        position = new Pair<>(x, y);
    }

    public void setPosition(int x, int y) {
        position = new Pair<>(x, y);
    }

    public void setX(int x) {
        position = new Pair<>(x, 0);
    }

    public void setY(int y) {
        position = new Pair<>(0, y);
    }

    public int getX() {
        return position.getKey();
    }

    public int getY() {
        return position.getValue();
    }

    public void changeX(int amount) {
        position = new Pair<>(position.getKey() + amount, position.getValue());
    }

    public void changeY(int amount) {
        position = new Pair<>(position.getKey(), position.getValue() + amount);
    }
}
