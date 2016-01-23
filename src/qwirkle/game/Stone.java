package qwirkle.game;

import java.awt.*;

/**
 * Created by chris on 15/01/16.
 */
public class Stone {
    Point location;
    Shape shape;
    Color color;

    public Piece(Color color, Shape shape) {
        this.color = color;
        this.shape = shape;
    }

    public enum Shape {
        HEART, STAR, SQUARE, CIRCLE, CROSS, DIAMOND, NULL
    }

    public enum Color {
        RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE
    }

    public void setLocation (int x, int y) {
        this.location = new Point(x, y);
    }

    public void setShape (Shape shape) {
        this.shape = shape;
    }

    public void setColor (Color color) {
        this.color = color;
    }

    public Point getLocation () {
        return location;
    }

    public Shape getShape () {
        return shape;
    }

    public Color getColor () {
        return color;
    }
}
