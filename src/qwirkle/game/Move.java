package qwirkle.game;

import java.util.List;

/**
 * Created by chris on 15/01/16.
 */
public class Move {
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public boolean isUniqueShape(List<Stone> list, Stone stone) {
        for (Stone s : list) {
            if (s.getShape() == stone.getShape()) {
                return false;
            }
        }
        return true;
    }

    public boolean isMatchingShape(List<Stone> list, Stone stone) {
        for (Stone s : list) {
            if (s.getShape() != stone.getShape()) {
                return false;
            }
        }
        return true;
    }

    public boolean isUniqueColor(List<Stone> list, Stone stone) {
        for (Stone s : list) {
            if (s.getColor() == stone.getColor()) {
                return false;
            }
        }
        return true;
    }

    public boolean isMatchingColor(List<Stone> list, Stone stone) {
        for (Stone s : list) {
            if (s.getColor() != stone.getColor()) {
                return false;
            }
        }
        return true;
    }
}
