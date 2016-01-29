package qwirkle.game;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import qwirkle.shared.Protocol;

import java.awt.*;

/**
 * @author Bouke Regnerus
 * @version 1.0
 * @since 2016-01-29
 */
public class Position {
    private Point position;

    /**
     * Initialize the position.
     */
    public Position(Point position) {
        this.position = position;
    }

    /**
     * Initialize the position.
     */
    public Position(int x, int y) {
        this.position = new Point(x, y);
    }

    /**
     * @return Return the position.
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     * @return Return the X value of the position.
     */
    public int getX() {
        return this.getPosition().x;
    }

    /**
     * @return Return the Y value of the position.
     */
    public int getY() {
        return this.getPosition().y;
    }

    /**
     * @return Return the position above this position.
     */
    public Position above() {
        return new Position(this.getX(), this.getY() + 1);
    }

    /**
     * @return Return the position below this position.
     */
    public Position below() {
        return new Position(this.getX(), this.getY() - 1);
    }

    /**
     * @return Return the position right from this position.
     */
    public Position right() {
        return new Position(this.getX() + 1, this.getY());
    }

    /**
     * @return Return the position left from this position.
     */
    public Position left() {
        return new Position(this.getX() - 1, this.getY());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31)// two randomly chosen prime numbers
                .append(position)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Position)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Position p = (Position) obj;

        return new EqualsBuilder().
                append(position, p.position).
                isEquals();
    }

    /**
     * @return Return the position as described by the protocol.
     */
    @Override
    public String toString() {
        return "" + this.getX() + Protocol.Server.Settings.DELIMITER2 + this.getY();
    }
}