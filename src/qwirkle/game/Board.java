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

    public void placeStone(Stone stone) {
        board.put(stone.getPoint(), stone);
    }



}


