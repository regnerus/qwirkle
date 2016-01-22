package qwirkle.game;

import java.awt.geom.Point2D;

/**
 * Created by chris on 15/01/16.
 */
public class Stone {
    Point2D point;

    public void setPoint (int x, int y) {
        this.point = new Point2D.Double(x, y);
    }

    public Point2D getPoint () {
        return point;
    }
}
