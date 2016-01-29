package qwirkle.game;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public class Bounds {
    int min, max;

    /**
     * Set initial bounds to 0 - 0.
     */
    public Bounds() {
        this.min = 0;
        this.max = 0;
    }

    /**
     * Set the max value of the bounds.
     *
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Set the min value of the bounds.
     *
     * @param min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return Return the absolute value between the min and max bounds.
     */
    public int getAbs() {
        return Math.abs(this.min) + this.max;
    }

    /**
     * @return Return the maximum value of the bounds.
     */
    public int getMax() {
        return max;
    }

    /**
     * @return Return the minimum value of the bounds.
     */
    public int getMin() { return min; }
}
