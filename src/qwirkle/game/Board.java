package qwirkle.game;

// apache
import org.apache.commons.collections4.ListUtils;
import qwirkle.shared.CliController;

// java
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by chris on 15/01/16.
 */
public class Board extends Move {

    private Map<Position, Stone> board;
    private Map<Position, Stone> possibleMoves;

    public static final int SPACE_AROUND_BOARD = 3;

    public Board () {
        this.possibleMoves = new HashMap<>();
        this.board = new HashMap<>();

        // TODO: randomize first stone
//        Stone firstMove = new Stone(Stone.Color.BLUE, Stone.Shape.CIRCLE);
//        placeStone(firstMove, 0, 0);
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
        if (this.validMove(stone)) {
            this.calculateBoardSize(stone.getLocation());
            board.put(stone.getLocation(), stone);
            stone.setPlaced(true);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return possible moves on the board
     * @return
     */
    public Map<Position, Stone> getPossibleMoves() {
        return possibleMoves;
    }

    public boolean placeStone(Stone stone, int x, int y) {
        stone.setLocation(x, y);

        return this.placeStone(stone);
    }

    public void removeStone(Stone stone) {
        // TODO: update possible moves
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

    // TODO: check if this is correct
    public void addPossibleMoves(Stone stone) {
        if (!board.keySet().contains(stone.getLocation())) {
            Stone above = board.get(new Position(stone.getLocation().getX(), stone.getLocation().getY() - 1));
            Stone below = board.get(new Position(stone.getLocation().getX(), stone.getLocation().getY() + 1));
            Stone left = board.get(new Position(stone.getLocation().getX() - 1, stone.getLocation().getY()));
            Stone right = board.get(new Position(stone.getLocation().getX() + 1, stone.getLocation().getY()));

            if (above != null)
                possibleMoves.put(above.getLocation(), above);

            if (below != null)
                possibleMoves.put(below.getLocation(), below);

            if (left != null)
                possibleMoves.put(left.getLocation(), left);

            if (right != null)
                possibleMoves.put(right.getLocation(), right);
        }
    }

    public Position coordinateToPosition(String x, String y) {
        char[] charsX = x.toCharArray();
        char[] charsY = y.toCharArray();

        int letterX = (int) Character.toUpperCase(charsX[0]);
        int letterY = (int) Character.toUpperCase(charsY[0]);

        int positionX = 0, positionY = 0;

        int ASCII_INTEGER = 64; //for upper case
        if(letterX<=90 & letterX>=65) {
            positionX += ((letterX - ASCII_INTEGER) * 10) - 10;
            positionX += Character.getNumericValue(charsX[1]);
            positionX += this.getBoundsX().getMin() - 1;

        }

        if(letterY<=90 & letterY>=65) {
            positionY += ((letterY - ASCII_INTEGER) * 10) - 10;
            positionY += Character.getNumericValue(charsY[1]);
            positionY += this.getBoundsY().getMin() - 1;
        }

        return new Position(positionX,positionY);

    }

    public Stone coordinateToStone(String x, String y) {
        return this.getStone(this.coordinateToPosition(x, y));
    }

    /**
     * Check if a stone is valid on this board
     * @param stone stone to validate
     * @return
     */
    public boolean isPossibleMove(Stone stone) {
        // TODO: add logic
        return true;
    }

    @Override
    public String toString() {
        String s = "";

        char sectionX = 'A', sectionY = 'A';
        int numberX = 0, numberY = 0;

        //Letter Row
        s += "    ";
        for (int x = this.getBoundsX().getMin() - SPACE_AROUND_BOARD; x <= this.getBoundsX().getMax() + SPACE_AROUND_BOARD; x++) {
            s += ((numberX % 10 == 0) ? sectionX : " ") + " ";

            if(numberX % 10 == 0) {
                sectionX++;
            }

            numberX++;
        }

        s += CliController.RETURN;

        //Number Row
        numberX = 0;

        s += "    ";
        for (int x = this.getBoundsX().getMin() - SPACE_AROUND_BOARD; x <= this.getBoundsX().getMax() + SPACE_AROUND_BOARD; x++) {
            s += numberX % 10 + " ";
            numberX++;
        }

        s = s + CliController.RETURN;

        //Board
        for(int y = this.getBoundsY().getMin() - SPACE_AROUND_BOARD; y <= this.getBoundsY().getMax() + SPACE_AROUND_BOARD; y++) {
            s += ((numberY % 10 == 0) ? sectionY : " ") + " ";
            s += numberY % 10 + " ";

            for (int x = this.getBoundsX().getMin() - SPACE_AROUND_BOARD; x <= this.getBoundsX().getMax() + SPACE_AROUND_BOARD; x++) {
                s += this.getStone(new Position(x, y)).toString() + " ";
            }

            s += "" + numberY % 10;
            s += " " + ((numberY % 10 == 0) ? sectionY : " ");

            if(numberY % 10 == 0) {
                sectionY++;
            }

            numberY++;
            s += CliController.RETURN;
        }

        //Number Row
        numberX = 0;

        s += "    ";
        for (int x = this.getBoundsX().getMin() - SPACE_AROUND_BOARD; x <= this.getBoundsX().getMax() + SPACE_AROUND_BOARD; x++) {
            s += numberX % 10 + " ";
            numberX++;
        }

        s = s + CliController.RETURN;

        //Letter Row
        numberX = 0;
        sectionX = 'A';

        s += "    ";
        for (int x = this.getBoundsX().getMin() - SPACE_AROUND_BOARD; x <= this.getBoundsX().getMax() + SPACE_AROUND_BOARD; x++) {
            s += ((numberX % 10 == 0) ? sectionX : " ") + " ";

            if(numberX % 10 == 0) {
                sectionX++;
            }

            numberX++;
        }

        s += CliController.RETURN;

        return s;
    }
}


