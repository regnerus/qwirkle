package qwirkle.game;

/**
 * Created by Bouke on 24/01/16.
 */
public class Points {

    int points;

    public Points () {
        this.points = 0;
    }

    public void addPoints(int points) {
        this.points = this.points + points;
    }

    public int getPoints() {
        return points;
    }
}
