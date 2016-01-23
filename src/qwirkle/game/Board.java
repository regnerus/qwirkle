package qwirkle.game;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chris on 15/01/16.
 */
public class Board {


    Map<Position, Stone> board;

    public Board () {
        this.board = new HashMap<>();
    }

    public Map<Position, Stone> getBoard () {
        return this.board;
    }

    private Bounds boundsX = new Bounds();
    private Bounds boundsY = new Bounds();

    private void calculateBoardSize (Position position) {
        int x = position.getX();
        int y = position.getY();

        if(x < boundsX.getMin()) {
            boundsX.setMin(x);
        }
        else if(x > boundsX.getMax()) {
            boundsX.setMax(x);
        }

        if(y < boundsY.getMin()) {
            boundsY.setMin(y);
        }
        else if(y > boundsY.getMax()) {
            boundsY.setMax(y);
        }
    }

    public Bounds getBoundsX () {
        return boundsX;
    }

    public Bounds getBoundsY () {
        return boundsY;
    }

    public void placeStone(Stone stone) {
        //TODO: Validate stone position with validateAdjacentPoint

        this.calculateBoardSize(stone.getLocation());
        board.put(stone.getLocation(), stone);
    }

    public void placeStone(Stone stone, int x, int y) {
        stone.setLocation(x, y);
        this.placeStone(stone);
    }

    public Stone getStone(Position location) {

        Stone stone = this.board.get(location);

        if (stone == null) {
            return new Stone(Stone.Color.NULL, Stone.Shape.NULL);
        }
        else {
            return stone;
        }
    }


//    private boolean validateAdjacentPoint(Stone currentStone, int directionX, int directionY) {
//        Point2D currentPosition = currentStone.getPoint();
//        Point2D adjacentPoint = new Point2D.Double(currentPosition.getX() - directionX, currentPosition.getY() - directionY);
//        Stone adjacentStone = this.getStone(adjacentPoint);
//
//        //TODO: Add validation for allowed situations.
//    }



}


