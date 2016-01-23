package qwirkle.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bouke on 23/01/16.
 */
public class PossibleMove {

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public boolean isUniqueShape(List<Stone> row, Stone stone) {
        for (Stone s : row) {
            if (s.getShape() != stone.getShape()) {
                return false;
            }
        }
        return true;
    }

    public boolean isMatchingShape(List<Stone> row, Stone stone) {
        for (Stone s : row) {
            if (s.getShape() == stone.getShape()) {
                return false;
            }
        }
        return true;
    }

    public boolean isUniqueColor(List<Stone> row, Stone stone) {
        for (Stone s : row) {
            if (s.getColor() != stone.getColor()) {
                return false;
            }
        }
        return true;
    }

    public boolean isMatchingColor(List<Stone> row, Stone stone) {
        for (Stone s : row) {
            if (s.getColor() == stone.getColor()) {
                return false;
            }
        }
        return true;
    }

    public List<Stone> getConnectedRow(Stone currentStone, Direction direction) {
        int offsetX = 0;
        int offsetY = 0;

        List<Stone> row = new ArrayList<>();

        Stone connectedStone = currentStone;
        Position initialLocation = currentStone.getLocation();

        while(connectedStone.getShape() != Stone.Shape.NULL) {
            switch (direction) {
                case UP:
                    offsetX--;
                    break;
                case DOWN:
                    offsetX++;
                    break;
                case LEFT:
                    offsetY--;
                    break;
                case RIGHT:
                    offsetY++;
                    break;
            }

//            connectedStone = Board.getStone(new Position(initialLocation.getX() + offsetX, initialLocation.getY() + offsetY));
            row.add(connectedStone);
        }

        return row;
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
