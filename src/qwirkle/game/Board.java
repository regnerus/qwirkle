package qwirkle.game;

import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by chris on 15/01/16.
 */
public class Board extends Move {
    Map<Position, Stone> board;

    public Board () {
        this.board = new HashMap<>();
    }

    public Map<Position, Stone> getBoard () {
        return this.board;
    }

    private Bounds boundsX = new Bounds();
    private Bounds boundsY = new Bounds();

    private void calculateBoardSize (Position position) {
        int x = position.getX();
        int y = position.getY();

        if(x < boundsX.getMin()) {
            boundsX.setMin(x);
        }
        else if(x > boundsX.getMax()) {
            boundsX.setMax(x);
        }

        if(y < boundsY.getMin()) {
            boundsY.setMin(y);
        }
        else if(y > boundsY.getMax()) {
            boundsY.setMax(y);
        }
    }

    public Bounds getBoundsX () {
        return boundsX;
    }

    public Bounds getBoundsY () {
        return boundsY;
    }

    public void placeStone(Stone stone) {
        // TODO: Validate stone position with validateAdjacentPoint

        this.calculateBoardSize(stone.getLocation());
        board.put(stone.getLocation(), stone);
    }

    public void placeStone(Stone stone, int x, int y) {
        stone.setLocation(x, y);
        this.placeStone(stone);
    }

    public Stone getStone(Position location) {

        Stone stone = this.board.get(location);

        if (stone == null) {
            return new Stone(Stone.Color.NULL, Stone.Shape.NULL);
        }
        else {
            return stone;
        }
    }

    public List<Stone> getDirection (Stone stone, Direction direction) {
        Position location = stone.getLocation();
        List<Stone> list = new ArrayList<>();

        Stone currentStone = stone;
        while (currentStone != null) {
            switch (direction) {
                case UP:
                    location = location.above();
                    break;
                case DOWN:
                    location = location.below();
                    break;
                case LEFT:
                    location = location.left();
                    break;
                case RIGHT:
                    location = location.right();
                    break;
            }

            if(stone != currentStone) {
                list.add(currentStone);
            }

            currentStone = this.board.get(location);
        }

        return list;
    }

    public List<Stone> getRow (Stone stone) {
        return ListUtils.union(this.getDirection(stone, Direction.LEFT), this.getDirection(stone, Direction.RIGHT));
    }

    public List<Stone> getColumn (Stone stone) {
        return ListUtils.union(this.getDirection(stone, Direction.UP), this.getDirection(stone, Direction.DOWN));
    }

    public boolean validateList (List<Stone> list, Stone stone) {
        if (this.isUniqueShape(list, stone) && this.isMatchingColor(list, stone)) {
            return true;
        } else if (this.isMatchingShape(list, stone) && this.isUniqueColor(list, stone)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean validMove (Stone stone) {
        List<Stone> row = this.getRow(stone);
        List<Stone> column = this.getColumn(stone);

        if(row.size() > 1 && column.size() > 1) {
            return validateList(row, stone) && validateList(column, stone);
        }
        else if (row.size() > 1) {
            return validateList(row, stone);
        }
        else if (column.size() > 1) {
            return validateList(column, stone);
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        String s = "";
        for(int y = this.getBoundsY().getMin(); y <= this.getBoundsY().getMax(); y++) {
            for (int x = this.getBoundsX().getMin(); x <= this.getBoundsX().getMax(); x++) {
                s = s + this.getStone(new Position(x, y)).toString() + " ";
            }
            s = s + "\n";
        }

        return s;
    }
}


