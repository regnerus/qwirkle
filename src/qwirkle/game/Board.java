package qwirkle.game;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by chris on 15/01/16.
 */
public class Board {


    Map<Point, Stone> board;

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public Board () {
        this.board = new HashMap<>();
    }

    public Map<Point, Stone> getBoard () {
        return this.board;
    }

    public void placeStone(Stone stone) {
        //TODO: Validate stone position with validateAdjacentPoint

        board.put(stone.getLocation(), stone);
    }

    public Stone getStone(Point location) {
        return board.get(location);
    }

    public List<Stone> getConnectedRow(Stone currentStone, Direction direction) {
        int offsetX = 0;
        int offsetY = 0;

        List<Stone> row = new ArrayList<>();

        Stone connectedStone = currentStone;
        Point initialLocation = currentStone.getLocation();

        while(connectedStone.getShape() != NULL) {
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

            connectedStone = getStone(new Point(initialLocation.x + offsetX, initialLocation.y + offsetY));
            row.add(connectedStone);
        }
    }

    public boolean validateConnectedRow(List<Stone> row) {
        Set<Stone.Shape> shapes = new HashSet<>();
        Set<Stone.Color> colors = new HashSet<>();
        Stone previousStone = null;

        boolean isUniqueShape = true;
        boolean isUniqueColor = true;
        boolean isSameShape = true;
        boolean isSameColor = true;

        for (Stone stone : row) {
            //Checks if the shape of all stones in the row is unique
            if(!shapes.contains(stone.getShape())) {
                shapes.add(stone.getShape());
            }
            else {
                isUniqueShape = false;
            }

            //Checks if the color of all stones in the row is unique
            if(!colors.contains(stone.getColor())) {
                colors.add(stone.getColor());
            }
            else {
                isUniqueColor = false;
            }

            //Checks if the shape of all stones in the row is the same
            if(previousStone != null && previousStone.getShape() != stone.getShape()) {
                isSameShape = false;
            }

            //Checks if the color of all stones in the row is the same
            if(previousStone != null && previousStone.getShape() != stone.getShape()) {
                isSameShape = false;
            }

            previousStone = stone;
        }

        //Returns true if the row has a combination of unique shapes with the same color OR a unique color with the same shapes.
        return (isUniqueShape && isSameColor) || (isUniqueColor && isSameShape);

    }




//    private boolean validateAdjacentPoint(Stone currentStone, int directionX, int directionY) {
//        Point2D currentPosition = currentStone.getPoint();
//        Point2D adjacentPoint = new Point2D.Double(currentPosition.getX() - directionX, currentPosition.getY() - directionY);
//        Stone adjacentStone = this.getStone(adjacentPoint);
//
//        //TODO: Add validation for allowed situations.
//    }



}


