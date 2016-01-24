package qwirkle.game;

import org.apache.commons.collections4.ListUtils;

import java.util.*;
import java.util.stream.Collectors;


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

    public boolean placeStone(Stone stone) {
        // TODO: Validate stone position with validateAdjacentPoint

        if(this.validMove(stone)) {
            this.calculateBoardSize(stone.getLocation());
            board.put(stone.getLocation(), stone);
            stone.setPlaced(true);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean placeStone(Stone stone, int x, int y) {
        stone.setLocation(x, y);
        return this.placeStone(stone);
    }

    public void removeStone(Stone stone) {
        this.board.remove(stone.getLocation());
        stone.setPlaced(false);
    }

    public int placeStones(List<Stone> stones) {
        List<List<Stone>> connectedColumns = new ArrayList<>();
        List<List<Stone>> connectedRows = new ArrayList<>();

        List<List<Stone>> connectedAll;
        List<Stone> placedStones = new ArrayList<>();
        int points = 0;

        for(Stone stone : stones) {
            if(this.placeStone(stone)) {
                placedStones.add(stone);
            }
            else {
                placedStones.forEach(this::removeStone);
                return -1;
            }
        }

        for(Stone stone : stones) {
            if(this.getColumn(stone).size() > 1) {
                if(connectedColumns.size() < 1) {
                    connectedColumns.add(this.getColumn(stone));
                }
                else {
                    connectedColumns.addAll(connectedColumns.stream().filter(list -> !list.equals(this.getColumn(stone))).map(list -> this.getColumn(stone)).collect(Collectors.toList()));

                }
            }

            if(this.getRow(stone).size() > 1) {
                if(connectedRows.size() < 1) {
                    connectedRows.add(this.getRow(stone));
                }
                else {
                    connectedRows.addAll(connectedRows.stream().filter(list -> !list.equals(this.getRow(stone))).map(list -> this.getRow(stone)).collect(Collectors.toList()));
                }
            }
        }

        connectedAll = ListUtils.union(connectedColumns, connectedRows);

        for(List<Stone> stonesList : connectedAll) {
            points = points + stonesList.size();
        }

        return points;
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

        if(direction == Direction.LEFT || direction == Direction.UP) {
            Collections.reverse(list);
        }

        return list;
    }

    public List<Stone> getRow (Stone stone, boolean includeSelf) {
        List<Stone> left = this.getDirection(stone, Direction.LEFT);
        List<Stone> right = this.getDirection(stone, Direction.RIGHT);

        if(includeSelf) {
            left.add(stone);
        }

        return ListUtils.union(left, right);
    }

    public List<Stone> getColumn (Stone stone, boolean includeSelf) {
        List<Stone> up = this.getDirection(stone, Direction.UP);
        List<Stone> down = this.getDirection(stone, Direction.DOWN);

        if(includeSelf) {
            up.add(stone);
        }

        return ListUtils.union(up, down);
    }

    public List<Stone> getRow (Stone stone) {
        return this.getRow(stone, true);
    }

    public List<Stone> getColumn (Stone stone) {
        return this.getColumn(stone, true);
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
        List<Stone> row = this.getRow(stone, false);
        List<Stone> column = this.getColumn(stone, false);

        if(this.board.size() < 1) {
            return true;
        }



        if(row.size() >= 1 && column.size() >= 1) {
            return validateList(row, stone) || validateList(column, stone);
        }
        else if (row.size() >= 1) {
            return validateList(row, stone);
        }
        else if (column.size() >= 1) {
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


