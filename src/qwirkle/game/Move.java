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

//    public boolean validateConnectedRow(List<Stone> row) {
//        Set<Stone.Shape> shapes = new HashSet<>();
//        Set<Stone.Color> colors = new HashSet<>();
//        Stone previousStone = null;
//
//        boolean isUniqueShape = true;
//        boolean isUniqueColor = true;
//        boolean isSameShape = true;
//        boolean isSameColor = true;
//
//        for (Stone stone : row) {
//            //Checks if the shape of all stones in the row is unique
//            if(!shapes.contains(stone.getShape())) {
//                shapes.add(stone.getShape());
//            }
//            else {
//                isUniqueShape = false;
//            }
//
//            //Checks if the color of all stones in the row is unique
//            if(!colors.contains(stone.getColor())) {
//                colors.add(stone.getColor());
//            }
//            else {
//                isUniqueColor = false;
//            }
//
//            //Checks if the shape of all stones in the row is the same
//            if(previousStone != null && previousStone.getShape() != stone.getShape()) {
//                isSameShape = false;
//            }
//
//            //Checks if the color of all stones in the row is the same
//            if(previousStone != null && previousStone.getShape() != stone.getShape()) {
//                isSameShape = false;
//            }
//
//            previousStone = stone;
//        }
//
//        //Returns true if the row has a combination of unique shapes with the same color OR a unique color with the same shapes.
//        return (isUniqueShape && isSameColor) || (isUniqueColor && isSameShape);
//
//    }
}
