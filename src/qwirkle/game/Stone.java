package qwirkle.game;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chris on 15/01/16.
 */
public class Stone {
    private Position location;
    private Shape shape;
    private Color color;
    private boolean isPlaced;

    public static final String ANSI_RESET = "\u001B[0m";

    public Stone(Color color, Shape shape) {
        this.color = color;
        this.shape = shape;
        this.isPlaced = false;
    }

    //Note: Important that NULL is the last value otherwise it will be used to generate random stones.
    public enum Shape {
        HEART, STAR, SQUARE, CIRCLE, CROSS, DIAMOND,
        NULL;
    }

    //Note: Important that NULL is the last value otherwise it will be used to generate random stones.
    public enum Color {
        RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE,
        NULL;
    }

    private static final Map<Shape, String> shapeMap = new HashMap<Shape, String>() {{
        put(Shape.HEART, "\u2665");
        put(Shape.STAR, "\u2737");
        put(Shape.SQUARE, "\u25A0");
        put(Shape.CIRCLE, "\u25CF");
        put(Shape.CROSS, "\u002B");
        put(Shape.DIAMOND, "\u25C6");
        put(Shape.NULL, "\u25A1");
    }};

    private static final Map<Color, String> colorMap = new HashMap<Color, String>() {{
        put(Color.RED, "\u001B[31m");
        put(Color.ORANGE, "\u001B[33m");
        put(Color.YELLOW, "\u001B[33m");
        put(Color.GREEN, "\u001B[32m");
        put(Color.BLUE, "\u001B[34m");
        put(Color.PURPLE, "\u001B[35m");
        put(Color.NULL, "\u001B[37m");
    }};


    public boolean isPlaced() {
        return this.isPlaced;
    }

    public void setLocation (int x, int y) {
        this.location = new Position(x, y);
    }

    public void setShape (Shape shape) {
        this.shape = shape;
    }

    public void setColor (Color color) {
        this.color = color;
    }

    public Position getLocation () {
        return location;
    }

    public Shape getShape () {
        return shape;
    }

    public Color getColor () {
        return color;
    }

    @Override
    public String toString() {
        return String.valueOf(colorMap.get(this.getColor()) + shapeMap.get(this.getShape()) + ANSI_RESET);
    }
}
