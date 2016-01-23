package qwirkle.game;

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
        return position.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null || !(obj instanceof Position))
            return false;

        Position p=(Position) obj;

        return p.position.equals(position);
    }

    @Override
    public String toString() {
        return "(" + this.getX() + "," + this.getY() + ")";
    }
}