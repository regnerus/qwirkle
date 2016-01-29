package qwirkle.game;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public class Points {

    int points;

    /**
     * Initialize points as 0.
     */
    public Points() {
        this.points = 0;
    }

    /**
     * Add points.
     *
     * @param p Amount of points to add.
     */
    public void addPoints(int p) {
        this.points = this.points + p;
    }

    /**
     * @return Return the amount of points.
     */
    public int getPoints() {
        return points;
    }
}
