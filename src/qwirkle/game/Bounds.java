package qwirkle.game;

/**
 * Created by Bouke on 23/01/16.
 */
public class Bounds {
    int min, max;

    public Bounds () {
        this.min = 0;
        this.max = 0;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getAbs() {
        return Math.abs(this.min) + this.max;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }
}
