package qwirkle.game;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.awt.*;

/**
 * Created by Bouke on 23/01/16.
 */
public class Position {
    private Point position;

    public Position(Point position) {
        this.position = position;
    }

    public Position(int x, int y) {
        this.position = new Point(x, y);
    }

    public Point getPosition() {
        return this.position;
    }

    public int getX() {
        return this.getPosition().x;
    }

    public int getY() {
        return this.getPosition().y;
    }

    public Position above() {
        return new Position(this.getX(), this.getY() - 1);
    }

    public Position below() {
        return new Position(this.getX(), this.getY() + 1);
    }

    public Position right() {
        return new Position(this.getX() + 1, this.getY());
    }

    public Position left() {
        return new Position(this.getX() - 1, this.getY());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
                append(position).
                toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null || !(obj instanceof Position))
            return false;

        if (obj == this)
            return true;

        Position p = (Position) obj;

        return new EqualsBuilder().
                append(position, p.position).
                isEquals();
    }

    @Override
    public String toString() {
        return "(" + this.getX() + "," + this.getY() + ")";
    }
}