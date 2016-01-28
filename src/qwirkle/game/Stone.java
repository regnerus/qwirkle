package qwirkle.game;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import qwirkle.shared.Protocol;

import java.util.HashMap;
import java.util.Map;

import static qwirkle.game.Stone.Color.RED;

/**
 * Created by chris on 15/01/16.
 */
public class Stone {
    private Position location;
    private Shape shape;
    private Color color;
    private boolean isPlaced;
    private boolean temporary;

    public static final String ANSI_RESET = "\u001B[0m";

    public Stone(Color color, Shape shape) {
        this.color = color;
        this.shape = shape;
        this.isPlaced = false;
    }

    /*
        Note: Important that NULL is the last value,
        otherwise it will be used to generate random stones.
     */
    public enum Shape {
        HEART, STAR, SQUARE, CIRCLE, CROSS, DIAMOND,
        NULL;
    }

    /*
        Note: Important that NULL is the last value,
        otherwise it will be used to generate random stones.
     */
    public enum Color {
        RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE,
        NULL;
    }

    private static final Map<Shape, String> SHAPE_MAP = new HashMap<Shape, String>() {
        {
            put(Shape.HEART, "\u2665");
            put(Shape.STAR, "\u2737");
            put(Shape.SQUARE, "\u25A0");
            put(Shape.CIRCLE, "\u25CF");
            put(Shape.CROSS, "\u002B");
            put(Shape.DIAMOND, "\u25C6");
            put(Shape.NULL, "\u25A1");
        }
    };

    private static final Map<Color, String> COLOR_MAP = new HashMap<Color, String>() {
        {
            put(RED, "\u001B[91m");
            put(Color.ORANGE, "\u001B[96m"); //Changed to CYAN for better readability
            put(Color.YELLOW, "\u001B[93m");
            put(Color.GREEN, "\u001B[92m");
            put(Color.BLUE, "\u001B[94m");
            put(Color.PURPLE, "\u001B[95m");
            put(Color.NULL, "\u001B[90m");
        }
    };

    private static final Map<Color, String> COLOR_MAP_DIM = new HashMap<Color, String>() {
        {
            put(RED, "\u001B[31m");
            put(Color.ORANGE, "\u001B[36m"); //Changed to CYAN for better readability
            put(Color.YELLOW, "\u001B[33m");
            put(Color.GREEN, "\u001B[32m");
            put(Color.BLUE, "\u001B[34m");
            put(Color.PURPLE, "\u001B[35m");
            put(Color.NULL, "\u001B[37m");
        }
    };

    public boolean isPlaced() {
        return this.isPlaced;
    }

    public void setPlaced(Boolean placed) {
        this.isPlaced = placed;
    }

    public void setPlaced() {
        this.setPlaced(true);
    }

    public void setLocation(Position position) {
        this.location = position;
    }

    public void setLocation(int x, int y) {
        this.location = new Position(x, y);
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Position getLocation() {
        return location;
    }

    public Shape getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public String toMove() {
        return this.toChars() + Protocol.Server.Settings.DELIMITER2 + this.location.toString();
    }

    public void setTemporary(Boolean value) {
        this.temporary = value;
    }

    public Boolean getTemporary() {
        return this.temporary;
    }

    public static Stone fromMove(String move) {
        String[] chars = move.split("\\" + Character.toString(Protocol.Server.Settings.DELIMITER2));

        Stone stone = Stone.fromChars(chars[0]);

        int x = 0;
        int y = 0;

        try {
            x = Integer.parseInt(chars[1]);
            y = Integer.parseInt(chars[2]);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        stone.setLocation(x, y);

        return stone;
    }

    public static Stone fromChars(String chars) {
        if (chars.length() != 2) {
            return null;
        }

        Color c;
        Shape s;

        switch (chars.charAt(0)) {
            case 'A':
                c = Color.RED;
                break;
            case 'B':
                c = Color.ORANGE;
                break;
            case 'C':
                c = Color.YELLOW;
                break;
            case 'D':
                c = Color.GREEN;
                break;
            case 'E':
                c = Color.BLUE;
                break;
            case 'F':
                c = Color.PURPLE;
                break;
            default:
                c = Color.NULL;
        }

        switch (chars.charAt(1)) {
            case 'F':
                s = Shape.HEART;
                break;
            case 'E':
                s = Shape.STAR;
                break;
            case 'D':
                s = Shape.SQUARE;
                break;
            case 'A':
                s = Shape.CIRCLE;
                break;
            case 'B':
                s = Shape.CROSS;
                break;
            case 'C':
                s = Shape.DIAMOND;
                break;
            default:
                s = Shape.NULL;
        }

        return new Stone(c, s);
    }

    public String toChars() {
        char c, s;

        switch (this.getColor()) {
            case RED:
                c = 'A';
                break;
            case ORANGE:
                c = 'B';
                break;
            case YELLOW:
                c = 'C';
                break;
            case GREEN:
                c = 'D';
                break;
            case BLUE:
                c = 'E';
                break;
            case PURPLE:
                c = 'F';
                break;
            default:
                return null;
        }

        switch (this.getShape()) {
            case HEART:
                s = 'F';
                break;
            case STAR:
                s = 'E';
                break;
            case SQUARE:
                s = 'D';
                break;
            case CIRCLE:
                s = 'A';
                break;
            case CROSS:
                s = 'B';
                break;
            case DIAMOND:
                s = 'C';
                break;
            default:
                return null;
        }

        return Character.toString(c) + Character.toString(s);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
                // if deriving: appendSuper(super.hashCode()).
                        append(shape).
                        append(color).
                        toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Stone))
            return false;
        if (obj == this)
            return true;

        Stone s = (Stone) obj;
        return new EqualsBuilder().
                // if deriving: appendSuper(super.equals(obj)).
                        append(shape, s.shape).
                        append(color, s.color).
                        isEquals();
    }

    @Override
    public String toString() {
        if(this.temporary) {
            return String.valueOf(COLOR_MAP_DIM.get(this.getColor()) +
                    SHAPE_MAP.get(this.getShape()) + ANSI_RESET);
        }
        else {
            return String.valueOf(COLOR_MAP.get(this.getColor()) +
                    SHAPE_MAP.get(this.getShape()) + ANSI_RESET);
        }

    }
}
