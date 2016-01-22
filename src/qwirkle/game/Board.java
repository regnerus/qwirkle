package qwirkle.game;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chris on 15/01/16.
 */
public class Board {


    Map<Point2D, Stone> board;

    public Board () {
        this.board = new HashMap<>();
    }

    public Map<Point2D, Stone> getBoard () {
        return this.board;
    }

    public void placeStone(Stone stone) {
        //TODO: Validate stone position with validateAdjacentPoint

        board.put(stone.getPoint(), stone);
    }

    public Stone getStone(Point2D point) {
        return board.get(point);
    }

    private boolean validateAdjacentPoint(Stone currentStone, int directionX, int directionY) {
        Point2D currentPosition = currentStone.getPoint();
        Point2D adjacentPoint = new Point2D.Double(currentPosition.getX() - directionX, currentPosition.getY() - directionY);
        Stone adjacentStone = this.getStone(adjacentPoint);

        //TODO: Add validation for allowed situations.
    }



}


